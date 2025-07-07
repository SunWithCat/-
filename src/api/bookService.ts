// frontend/src/api/bookService.ts
import axios from 'axios';
import type { Book } from '../types/book';

// 定义你的后端 API 的基础 URL
const API_BASE_URL = 'http://localhost:8080/api';

// 创建一个 axios 实例，可以进行一些全局配置
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
});

// 导出一个函数，用于获取所有图书
export const getAllBooks = async (): Promise<Book[]> => {
  try {
    const response = await apiClient.get('/books');
    return response.data;
  } catch (error) {
    console.error('获取图书列表失败:', error);
    // 实际项目中可能会有更复杂的错误处理
    throw error;
  }
};

// 未来我们可以在这里添加更多函数，比如：
// export const getBookById = async (id: number): Promise<Book> => { ... };
// export const createBook = async (bookData: Omit<Book, 'bookId'>): Promise<Book> => { ... };