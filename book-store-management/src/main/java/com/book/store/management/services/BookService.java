package com.book.store.management.services;
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
}
