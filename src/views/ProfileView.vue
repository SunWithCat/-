<template>
  <div class="profile-page">
    <h1>个人中心</h1>

    <div v-if="currentUser" class="profile-card">
      <div class="avatar">
        <span>{{ currentUser.username.charAt(0).toUpperCase() }}</span>
      </div>
      <div class="user-info">
        <h2>{{ currentUser.username }}</h2>
        <p>邮箱: {{ currentUser.email }}</p>
        <p>角色: {{ currentUser.role }}</p>
      </div>
    </div>

    <div class="borrowed-section">
      <h2>我的借阅</h2>
      <div v-if="borrowedBooks.length > 0" class="borrowed-list">
        <table>
          <thead>
            <tr>
              <th>书名</th>
              <th>作者</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="book in borrowedBooks" :key="book.bookId">
              <td>{{ book.title }}</td>
              <td>{{ book.author }}</td>
              <td>
                <button @click="returnBook(book)" class="btn-return">还 书</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else class="no-books">
        <p>您当前没有借阅任何书籍。快去 <router-link to="/books">图书大厅</router-link> 看看吧！</p>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useAuth } from '../store/useAuth';
import { bookList } from '../data/mockBooks';
import type { Book } from '../types/book';

const { currentUser } = useAuth();

// 1. 计算属性：从总图书列表中筛选出“已借出”的书籍
// 在真实应用中，这里应该是一个 API 请求，获取该用户关联的借阅记录
const borrowedBooks = computed(() => 
  bookList.value.filter(book => book.status === 'BORROWED')
);

// 2. 还书的逻辑函数
function returnBook(bookToReturn: Book) {
  // 弹出确认框，增加用户体验
  if (confirm(`您确定要归还《${bookToReturn.title}》吗？`)) {
    // 在总列表中找到这本书
    const bookInList = bookList.value.find(b => b.bookId === bookToReturn.bookId);
    if (bookInList) {
      // 将状态改回“可借阅”
      bookInList.status = 'AVAILABLE';
      alert(`《${bookToReturn.title}》归还成功！`);
    }
  }
}
</script>

<style scoped>
.profile-page h1 {
  text-align: center;
  margin-bottom: 30px;
}
.profile-card {
  background: white;
  border-radius: 8px;
  padding: 24px;
  display: flex;
  align-items: center;
  gap: 20px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  margin-bottom: 40px;
}
.avatar {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: #409eff;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.5rem;
  font-weight: bold;
}
.user-info h2 {
  margin: 0 0 10px;
}
.user-info p {
  margin: 5px 0;
  color: #666;
}
.borrowed-section h2 {
  border-bottom: 2px solid #eee;
  padding-bottom: 10px;
  margin-bottom: 20px;
}
.borrowed-list table {
  width: 100%;
  border-collapse: collapse;
}
.borrowed-list th, .borrowed-list td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}
.borrowed-list th {
  background-color: #f8f8f8;
}
.btn-return {
  background-color: #67c23a;
  color: white;
  border: none;
  padding: 6px 12px;
  border-radius: 4px;
  cursor: pointer;
}
.btn-return:hover {
  background-color: #85ce61;
}
.no-books {
  text-align: center;
  padding: 30px;
  color: #999;
}
</style>
