<template>
    <div class="admin-page">
        <h1>后台管理系统</h1>

        <div class="admin-section">
            <h2>添加新图书</h2>
            <form @submit.prevent="addBook" class="add-book-form">
                <div class="form-row">
                    <input type="text" v-model="newBook.title" placeholder="书名" required>
                    <input type="text" v-model="newBook.author" placeholder="作者" required>
                </div>
                <div class="form-row">
                    <input type="text" v-model="newBook.isbn" placeholder="ISBN">
                    <input type="text" v-model="newBook.publisher" placeholder="出版社">
                </div>
                <div class="form-row">
                    <input type="number" v-model.number="newBook.publicationYear" placeholder="出版年份">
                    <input type="text" v-model="newBook.coverUrl" placeholder="封面图片URL">
                </div>
                <button type="submit">确认添加</button>
            </form>
        </div>
        <div class="book-management-list">
            <table>
                <thead>
                    <tr>
                        <th>书名</th>
                        <th>作者</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="book in bookList" :key="book.bookId">
                        <td>{{ book.title }}</td>
                        <td>{{ book.author }}</td>
                        <td>{{ book.status }}</td>
                        <td>
                            <button @click="deleteBook(book.bookId)" class="btn-delete">删除</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="admin-section">
            <h2>图书管理列表</h2>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { bookList } from '../data/mockBooks';
import type { Book } from '../types/book';

// 1. 创建一个响应式对象来绑定表单数据
const newBook = ref<Omit<Book, 'bookId' | 'status'>>({
    title: '',
    author: '',
    isbn: '',
    publisher: '',
    publicationYear: new Date().getFullYear(),
    coverUrl: ''
});

// 2. 添加图书的函数
function addBook() {
    // 简单的前端验证
    if (!newBook.value.title || !newBook.value.author) {
        alert('书名和作者不能为空！');
        return;
    }

    // 创建一个完整的 Book 对象
    const bookToAdd: Book = {
        // 生成一个唯一的 bookId (在真实后端中由数据库完成)
        bookId: Date.now(),
        ...newBook.value,
        status: 'AVAILABLE' // 新添加的书默认状态为“可借阅”
    };

    // 将新书添加到我们的模拟数据列表的开头
    bookList.value.unshift(bookToAdd);

    alert(`《${bookToAdd.title}》添加成功！`);

    // 3. 清空表单，方便下次输入
    newBook.value = {
        title: '',
        author: '',
        isbn: '',
        publisher: '',
        publicationYear: new Date().getFullYear(),
        coverUrl: ''
    };
}
// src/views/AdminView.vue -> script setup
// ... 在 addBook 函数下面，添加新函数
function deleteBook(bookId: number) {
  const bookToDelete = bookList.value.find(b => b.bookId === bookId);
  if (!bookToDelete) return;

  if (confirm(`确定要永久删除《${bookToDelete.title}》吗？此操作不可撤销！`)) {
    // 找到要删除的书的索引
    const index = bookList.value.findIndex(b => b.bookId === bookId);
    if (index !== -1) {
      // 从数组中移除
      bookList.value.splice(index, 1);
    }
  }
}
</script>

<style scoped>
.admin-page {
    max-width: 900px;
    margin: auto;
}

h1 {
    text-align: center;
    margin-bottom: 30px;
}

.admin-section {
    background: white;
    padding: 24px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 30px;
}

.add-book-form {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.form-row {
    display: flex;
    gap: 15px;
}

.form-row input {
    flex: 1;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.add-book-form button {
    padding: 10px 15px;
    background-color: #409eff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    align-self: flex-start;
    /* 让按钮不要撑满整行 */
}

/* src/views/AdminView.vue -> style scoped */
/* ... 在已有样式下面，添加新样式 */
.book-management-list table {
  width: 100%;
  border-collapse: collapse;
}
.book-management-list th, .book-management-list td {
  padding: 10px;
  border-bottom: 1px solid #eee;
  text-align: left;
}
.btn-delete {
  background-color: #f56c6c;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
}
</style>