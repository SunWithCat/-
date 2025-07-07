import { ref } from 'vue';
import type { User } from '../types/user';

// 1. 定义一个响应式的变量来存储当前用户信息
const currentUser = ref<User | null>(null);

// 2. 定义一个函数来设置或更新当前用户信息
function setCurrentUser(user: User | null) {
  currentUser.value = user;
}

// 3. 导出一个包含 currentUser 和 setCurrentUser 的对象
export function useAuth() {
  return {
    currentUser,
    setCurrentUser,
  };
}
