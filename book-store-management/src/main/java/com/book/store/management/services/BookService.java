package com.book.store.management.services;
import com.book.store.management.payloads.BookDAO;

import java.util.List;

public interface BookService {

    BookDAO createBook(BookDAO book);

    BookDAO updateBook(BookDAO book, Long bookId);

    BookDAO getBookById(Long bookId);

    List<BookDAO> getAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    void deleteBook(Long bookId);

    List<BookDAO> getBooksByAuthor(Long authorId);

    List<BookDAO> searchBooks(String keyword);
}
