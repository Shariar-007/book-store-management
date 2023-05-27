package com.book.store.management.repositories;

import com.book.store.management.entity.Author;
import com.book.store.management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
}
