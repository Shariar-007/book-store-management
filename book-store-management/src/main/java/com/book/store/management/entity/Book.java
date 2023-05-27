package com.book.store.management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="books")
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(nullable = false)
    private String ISBN;

    @Column(name = "published_year")
    private Date yearOfPublication;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Author> author = new ArrayList<>();
}
