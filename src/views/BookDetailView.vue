<template>
  <div class="book-detail-page">
    <div v-if="book" class="book-content">
      <div class="cover">
        <img src="../assets/book.svg" :alt="book.title">
      </div>
      <div class="info">
        <h1>{{ book.title }}</h1>
        <p><strong>作者:</strong> {{ book.author }}</p>
        <p><strong>出版社:</strong> {{ book.publisher }} ({{ book.publicationYear }})</p>
        <p><strong>ISBN:</strong> {{ book.isbn }}</p>
        <p><strong>状态:</strong> 
          <span :class="['status-tag', statusInfo.className]">{{ statusInfo.text }}</span>
        </p>

        <div class="actions">
          <button v-if="book.status === 'AVAILABLE'" @click="borrowBook" class="btn-borrow">
            借 阅
          </button>
          <button v-else-if="book.status === 'BORROWED'" @click="reserveBook" class="btn-reserve">
            预 约
          </button>
          <p v-else-if="book.status === 'RESERVED'" class="reserved-text">
            您已预约此书，请等待通知 🕒
          </p>
        </div>

        <div v-if="operationMessage" class="feedback-message">
          {{ operationMessage }}
        </div>

        <router-link to="/books" class="back-button">返回图书列表</router-link>
      </div>
    </div>
    
    <div v-else class="not-found">
      </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'; // 引入 ref
import { useRoute } from 'vue-router';
import { bookList } from '../data/mockBooks';
import defaultCover from '../assets/vue.svg';

const route = useRoute();
const bookId = Number(route.params.id);

// book 现在不再是 computed，因为我们需要直接修改它。
// 我们从共享的 bookList 中找到对应的书的引用。
// 这样，我们在这里修改它，其他地方（如图书列表页）也会同步更新！
const bookRef = bookList.value.find(b => b.bookId === bookId);
const book = ref(bookRef); // 用 ref 包装一下，确保模板响应性

const statusInfo = computed(() => {
    if (!book.value) return { text: '', className: '' };
    switch (book.value.status) {
        case 'AVAILABLE': return { text: '可借阅', className: 'status-available' };
        case 'BORROWED': return { text: '已借出', className: 'status-borrowed' };
        case 'RESERVED': return { text: '已预约', className: 'status-reserved' };
        default: return { text: '未知', className: 'status-unknown' };
    }
});

// ==================== 新增逻辑函数 ====================

// 操作反馈信息
const operationMessage = ref('');

// 借书函数
function borrowBook() {
  if (book.value && book.value.status === 'AVAILABLE') {
    // 修改图书状态
    book.value.status = 'BORROWED';
    // 给出用户反馈
    operationMessage.value = '✅ 借阅成功！此书已加入您的借阅列表。';
    // 简单模拟，让消息过几秒后消失
    setTimeout(() => { operationMessage.value = '' }, 4000);
  }
}

// 预约函数
function reserveBook() {
  if (book.value && book.value.status === 'BORROWED') {
    // 修改图书状态
    book.value.status = 'RESERVED';
    // 给出用户反馈
    operationMessage.value = '✅ 预约成功！图书到馆后会通知您。';
    setTimeout(() => { operationMessage.value = '' }, 4000);
  }
}

</script>

<style scoped>
.book-detail-page {
  padding: 20px;
}
.book-content {
  display: flex;
  gap: 30px;
  background: white;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}
.cover img {
  width: 250px;
  border-radius: 8px;
}
.info h1 {
  margin: 0 0 20px 0;
}
.info p {
  margin: 10px 0;
  font-size: 1rem;
  color: #333;
}
.status-tag {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 0.9rem;
  color: white;
}
.actions {
    margin-top: 30px;
}
.actions button {
    padding: 10px 20px;
    font-size: 1rem;
    background-color: #409eff;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
}
.not-found {
  text-align: center;
  padding: 50px;
}
.back-button {
  display: inline-block;
  margin-top: 20px;
  padding: 10px 20px;
  background: #409eff;
  color: white;
  text-decoration: none;
  border-radius: 6px;
}

.status-available { background-color: #67c23a; }
.status-borrowed { background-color: #f56c6c; }
.status-reserved { background-color: #e6a23c; }
.status-unknown { background-color: #909399; }
.info p {
  line-height: 1.6;
}
.actions {
  margin-top: 30px;
  min-height: 50px; /* 给一个最小高度防止布局跳动 */
}
.actions button {
    padding: 10px 20px;
    font-size: 1rem;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.2s;
}
.btn-borrow { background-color: #409eff; }
.btn-borrow:hover { background-color: #66b1ff; }

.btn-reserve { background-color: #e6a23c; }
.btn-reserve:hover { background-color: #ebb563; }

.reserved-text {
    color: #e6a23c;
    font-weight: bold;
}

.feedback-message {
    margin-top: 15px;
    padding: 10px;
    background-color: #f0f9eb;
    color: #67c23a;
    border-radius: 4px;
    border: 1px solid #e1f3d8;
}

.back-button {
  display: inline-block;
  margin-top: 20px;
  padding: 8px 16px;
  background: #777;
  color: white;
  text-decoration: none;
  border-radius: 6px;
}
</style>