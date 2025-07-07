<template>
  <div class="book-card">
    <img :src="defaultCover" alt="Book Cover" class="book-cover" />
    <div class="book-info">
      <h3 class="book-title" :title="book.title">{{ book.title }}</h3>
      <p class="book-author">{{ book.author }}</p>
      <div class="book-quantity-wrapper">
         <span 
           :class="['book-quantity', book.quantity > 0 ? 'quantity-available' : 'quantity-unavailable']"
         >
           库存: {{ book.quantity }}
         </span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Book } from '../types/book';
// 暂时用一个固定的默认封面
import defaultCover from '../assets/book.svg';

const props = defineProps<{
  book: Book
}>();
</script>

<style scoped>
.book-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  background-color: white;
  transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
  cursor: pointer;
}

.book-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.book-cover {
  width: 100%;
  height: 200px;
  object-fit: contain; /* 改为 contain 防止图片变形 */
  margin-bottom: 12px;
  border-radius: 4px;
}

.book-info {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
}

.book-title {
  font-size: 1rem;
  font-weight: 600;
  margin: 0 0 8px 0;
  /* 防止标题过长  */
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.book-author {
  font-size: 0.875rem;
  color: #666;
  margin: 0 0 12px 0;
}

.book-status-wrapper {
    margin-top: auto; /* 将状态标签推到底部 */
}

.book-status {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 0.75rem;
  color: white;
  font-weight: 500;
}

.status-available { background-color: #67c23a; } /* 绿色 */
.status-borrowed { background-color: #f56c6c; } /* 红色 */
.status-reserved { background-color: #e6a23c; } /* 黄色 */
.status-unknown { background-color: #909399; } /* 灰色 */
/* ... 大部分样式不变 ... */
.book-quantity-wrapper {
  margin-top: auto;
}
.book-quantity {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 500;
}
.quantity-available {
  background-color: #f0f9eb;
  color: #67c23a;
}
.quantity-unavailable {
  background-color: #f4f4f5;
  color: #909399;
}
</style>