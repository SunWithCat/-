<template>
  <header class="main-header">
    <nav>
      <router-link to="/">登录</router-link>
      <router-link to="/books">图书大厅</router-link>
      <router-link to="/profile">个人中心</router-link>
      <router-link to="/admin">后台管理</router-link>
    </nav>
  </header>
  <main>
    <router-view />
  </main>
</template>

<style>
/* 移除 scoped，让这些样式成为全局样式 */
body {
  margin: 0;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  background-color: #f4f5f7;
}
</style>

<style scoped>
.main-header {
  background-color: white;
  padding: 0 24px;
  border-bottom: 1px solid #e0e0e0;
  height: 60px;
  display: flex;
  align-items: center;
}

nav {
  display: flex;
  gap: 20px;
}

nav a {
  text-decoration: none;
  color: #333;
  font-weight: 500;
  padding: 8px 12px;
  border-radius: 6px;
  transition: background-color 0.2s;
}

/* 为当前激活的路由链接添加样式 */
nav a.router-link-exact-active {
  color: #409eff;
  background-color: #ecf5ff;
}

main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}
</style>