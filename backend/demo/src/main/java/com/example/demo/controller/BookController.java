// backend/src/main/java/com/example/librarysystem/controller/BookController.java
package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books") // 定义所有接口的统一前缀
@CrossOrigin // 允许所有来源的跨域请求，方便本地前后端联调
public class BookController {

    @Autowired // Spring 自动帮我们注入之前创建的 BookRepository 实例
    private BookRepository bookRepository;

    // 创建一个处理 GET 请求的接口，路径为 /api/books
    @GetMapping
    public List<Book> getAllBooks() {
        // 调用 JpaRepository 自带的 findAll() 方法，查询所有图书
        return bookRepository.findAll();
    }
}