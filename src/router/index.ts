// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/', // 网站的根路径
    name: 'Login',
    // 使用动态导入，实现路由懒加载，优化性能
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/books', // 图书列表页的路径
    name: 'BookList',
    component: () => import('../views/BookListView.vue')
  },
  // --- 新增的路由规则 ---
  {
    // `:id` 就是一个动态参数，它可以匹配任何数字或字符串
    path: '/books/:id',
    name: 'BookDetail',
    component:() => import('../views/BookDetailView.vue')
  },
  // --- 新增个人中心路由 ---
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('../views/ProfileView.vue')
  },
  // --- 新增管理员路由 ---
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../views/AdminView.vue')
  }
];

const router = createRouter({
  history: createWebHistory(), // 使用 HTML5 History 模式
  routes: routes
});

export default router;