<template>
  <div class="login-container">
    <div class="login-box">
      <h1>图书管理系统</h1>
      <form @submit.prevent="handleLogin" class="login-form">
        <div class="form-group">
          <label for="username">用户名</label>
          <input 
            id="username"
            type="text" 
            v-model="username" 
            placeholder="请输入用户名" 
            required
          />
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input 
            id="password"
            type="password" 
            v-model="password" 
            placeholder="请输入密码" 
            required
          />
        </div>
        <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
        <button type="submit" class="login-button">登 录</button>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { login } from '../api/authService'; // 导入登录服务
import { useAuth } from '../store/useAuth';

const username = ref('');
const password = ref('');
const errorMessage = ref(''); // 用于存放错误信息

const { setCurrentUser } = useAuth();

const router = useRouter(); // 获取路由实例，用于登录后跳转

// // 登录处理函数
// async function handleLogin() {
//   // 1. 简单的前端校验
//   if (!username.value || !password.value) {
//     errorMessage.value = '用户名和密码不能为空！';
//     return;
//   }
  
//   // 2. 清空之前的错误信息
//   errorMessage.value = '';

//   // 3. 调用后端 API 进行登录 (这部分我们稍后实现)
//   console.log('准备使用以下信息登录:');
//   console.log('用户名:', username.value);
//   console.log('密码:', password.value);
  
//   // 模拟登录成功，并跳转到个人中心
//   alert('登录逻辑待实现！现在将模拟登录成功并跳转。');
//   router.push('/profile');

//   // 模拟登录失败
//   // errorMessage.value = '用户名或密码错误！';
// }
async function handleLogin() {
  if (!username.value || !password.value) {
    errorMessage.value = '用户名和密码不能为空！';
    return;
  }
  errorMessage.value = '';

  try {
    const response = await login(username.value, password.value);
    console.log('登录成功:', response);
    
    // ⚠️【重要】真实项目中，这里应该将 token 和用户信息存入 Pinia 等状态管理器
    setCurrentUser(response);
    alert(`登录成功！欢迎你，${response.username}！`);
    
    // 跳转到个人中心
    router.push('/profile');
    
  } catch (error) {
    // API 调用失败（比如401 Unauthorized）
    console.error('登录失败:', error);
    errorMessage.value = '用户名或密码错误！';
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh; /* 占满视窗高度 */
  background-color: #f4f5f7;
}
.login-box {
  width: 400px;
  padding: 40px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  text-align: center;
}
.login-box h1 {
  margin-bottom: 30px;
  font-size: 1.8rem;
  color: #333;
}
.form-group {
  margin-bottom: 20px;
  text-align: left;
}
.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: #555;
}
.form-group input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box; /* 保证 padding 不会撑大宽度 */
}
.login-button {
  width: 100%;
  padding: 12px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
  margin-top: 10px;
  transition: background-color 0.2s;
}
.login-button:hover {
  background-color: #66b1ff;
}
.error-message {
  color: #f56c6c;
  margin-bottom: 15px;
  text-align: left;
}
</style>
