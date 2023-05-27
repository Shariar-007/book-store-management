package com.book.store.management.services;
import com.book.store.management.entity.Author;
import com.book.store.management.entity.Book;
import com.book.store.management.payloads.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    BookDTO createBook(BookDTO book);

    BookDTO updateBook(BookDTO book, Long bookId);

    BookDTO getBookById(Long bookId);

    List<BookDTO> getAllBooks();

    void deleteBook(Long bookId);

    List<Book> getBooksByAuthor(Long authorId);

    List<Book> searchBooks(String keyword);
}
