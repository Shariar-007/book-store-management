package com.book.store.management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="authors")
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name", length = 100, nullable = false)
    private String name;

    private String email;

    @Column(name = "biography", length = 500)
    private String biography;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
