# 图书馆管理系统

这是一个基于 Vue 3 和 Spring Boot 构建的全栈图书馆管理系统。

## 功能特性

*   **用户认证:** 用户可以登录系统。
*   **图书浏览:** 用户可以查看图书馆中的所有图书。
*   **图书详情:** 用户可以查看单本图书的详细信息。
*   **个人中心:** 用户可以查看自己的个人信息。
*   **管理员面板:** 管理员可以进行图书管理等操作。

## 技术栈

### 前端

*   **框架:** [Vue.js 3](https://vuejs.org/)
*   **构建工具:** [Vite](https://vitejs.dev/)
*   **语言:** [TypeScript](https://www.typescriptlang.org/)
*   **状态管理:** [Pinia](https://pinia.vuejs.org/)
*   **路由:** [Vue Router](https://router.vuejs.org/)
*   **HTTP 客户端:** [Axios](https://axios-http.com/)

### 后端

*   **框架:** [Spring Boot](https://spring.io/projects/spring-boot)
*   **语言:** Java 17
*   **数据持久化:** [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
*   **数据库:** [H2](https://www.h2database.com/html/main.html) (开发环境), [MySQL](https://www.mysql.com/)
*   **工具:** [Lombok](https://projectlombok.org/)
*   **数据：** librarysystem.sql

## 运行项目

### 前端

1.  进入项目根目录
2.  安装依赖: `npm install`
3.  启动开发服务器: `npm run dev`

### 后端

1.  使用 IDE (如 IntelliJ IDEA) 打开 `backend/demo` 项目
2.  使用librarysystem.sql创建数据库
3.  启动 Spring Boot 应用



