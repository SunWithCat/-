// src/data/mockUser.ts
import { ref } from 'vue';

// 定义用户接口类型
export interface User {
  userId: number;
  username: string;
  email: string;
  role: 'STUDENT' | 'TEACHER' | 'ADMIN';
}

// 导出一个模拟的当前用户
export const currentUser = ref<User>({
  userId: 101,
  username: 'Lazyiou',
  email: 'lazyiou@example.com',
  role: 'STUDENT'
});