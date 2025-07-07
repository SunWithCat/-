// src/data/mockBooks.ts
import type { Book } from '../types/book';

// 导出一个响应式的数据数组，方便多处使用
import { ref } from 'vue';

export const bookList = ref<Book[]>([
  {
    bookId: 1,
    title: '深入理解计算机系统',
    author: 'Randal E. Bryant',
    isbn: '9787111544837',
    publisher: '机械工业出版社',
    publicationYear: 2016,
    status: 'AVAILABLE',
    coverUrl: 'https://img3.doubanio.com/view/subject/l/public/s28782061.jpg'
  },
  {
    bookId: 2,
    title: 'Vue.js 设计与实现',
    author: '霍春阳',
    isbn: '9787115603893',
    publisher: '人民邮电出版社',
    publicationYear: 2022,
    status: 'BORROWED',
    coverUrl: 'https://img1.doubanio.com/view/subject/l/public/s34226398.jpg'
  },
  {
    bookId: 3,
    title: '代码整洁之道',
    author: 'Robert C. Martin',
    isbn: '9787115216879',
    publisher: '人民邮电出版社',
    publicationYear: 2010,
    status: 'RESERVED',
    coverUrl: 'https://img9.doubanio.com/view/subject/l/public/s4418696.jpg'
  },
  {
    bookId: 4,
    title: 'Flutter 实战',
    author: '杜文',
    isbn: '9787111612567',
    publisher: '机械工业出版社',
    publicationYear: 2018,
    status: 'AVAILABLE',
  }
]);