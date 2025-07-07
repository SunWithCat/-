package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private Integer bookId;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Author", nullable = false)
    private String author;

    @Column(name = "Quantity")
    private Integer quantity;

    // 一本书可以有多条借阅记录
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore // 在序列化时忽略此字段，打破循环
    private List<BorrowRecord> borrowRecords;

    // 一本书可以有多条预约记录
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore // 在序列化时忽略此字段，打破循环
    private List<Reservation> reservations;
}