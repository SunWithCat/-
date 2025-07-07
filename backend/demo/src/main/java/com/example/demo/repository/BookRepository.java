// backend/src/main/java/com/example/librarysystem/repository/BookRepository.java
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // 就这么简单！
    // JpaRepository<Book, Integer> 意味着这个 Repository 是用来管理 Book 实体类的，
    // 并且 Book 的主键（Id）类型是 Integer。
    // Spring Data JPA 会自动为你提供 findAll(), findById(), save(), delete() 等一系列方法。
}