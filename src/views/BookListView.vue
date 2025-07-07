<template>
  <div class="book-list-page">
    <h1>图书大厅 📚</h1>
    <div class="search-bar">
      <input 
        type="text" 
        v-model="searchQuery" 
        placeholder="输入书名关键字搜索..."
      />
    </div>

    <div v-if="isLoading" class="loading-state">
      <p>正在努力加载图书...</p>
    </div>

    <div v-else-if="error" class="error-state">
      <p>{{ error }}</p>
    </div>

    <div v-else-if="filteredBooks.length === 0" class="no-results">
      <p>抱歉，没有找到匹配的图书 😔</p>
    </div>

    <div v-else class="book-grid">
      <router-link 
        v-for="book in filteredBooks" 
        :key="book.bookId" 
        :to="`/books/${book.bookId}`"
        class="book-card-link"
      >
        <BookCard :book="book" />
      </router-link>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import BookCard from '../components/BookCard.vue';
import { getAllBooks } from '../api/bookService'; // 导入我们的 API 服务
import type { Book } from '../types/book';

// --- 数据和状态定义 ---
const bookList = ref<Book[]>([]); // 初始为空数组
const searchQuery = ref('');
const isLoading = ref(true); // 新增：加载状态，默认为 true
const error = ref<string | null>(null); // 新增：错误信息状态

// --- API 调用 ---
// onMounted 是一个生命周期钩子，它会在组件挂载到页面上后立即执行
onMounted(async () => {
  try {
    // 调用 API 获取数据
    bookList.value = await getAllBooks();
  } catch (err) {
    // 如果 API 调用失败，设置错误信息
    error.value = '加载图书列表失败，请检查后端服务是否开启，或稍后再试。';
  } finally {
    // 无论成功还是失败，最后都设置加载状态为 false
    isLoading.value = false;
  }
});

// --- 计算属性 ---
const filteredBooks = computed(() => {
  if (!searchQuery.value) {
    return bookList.value;
  }
  return bookList.value.filter(book => 
    book.title.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});
</script>

<style scoped>
/* ... 此处样式可以保持不变，也可以按需添加 loading 和 error 的样式 ... */
.loading-state, .error-state, .no-results {
  text-align: center;
  color: #666;
  margin-top: 40px;
  padding: 20px;
}
.error-state {
  color: #f56c6c;
  background-color: #fef0f0;
  border: 1px solid #fde2e2;
  border-radius: 8px;
}
.book-card-link {
  text-decoration: none;
  color: inherit;
}
.book-list-page {
  padding: 20px;
}

h1 {
  margin-bottom: 24px;
  text-align: center;
}

/* 新增：搜索框样式 */
.search-bar {
  margin-bottom: 24px;
  text-align: center;
}
.search-bar input {
  width: 50%;
  max-width: 400px;
  padding: 10px 15px;
  border-radius: 20px;
  border: 1px solid #ccc;
  font-size: 1rem;
}
.search-bar input:focus {
  outline: none;
  border-color: #409eff;
  box-shadow: 0 0 5px rgba(64, 158, 255, 0.5);
}

.book-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 24px;
}

/* 新增：没有结果时的提示样式 */
.no-results {
  text-align: center;
  color: #666;
  margin-top: 40px;
}
</style>