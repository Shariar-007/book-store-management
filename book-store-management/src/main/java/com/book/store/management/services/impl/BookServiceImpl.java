package com.book.store.management.services.impl;

import com.book.store.management.entity.Book;
import com.book.store.management.payloads.BookDTO;
import com.book.store.management.repositories.BookRepo;
import com.book.store.management.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDTO createBook(BookDTO book) {


        return null;
    }

    @Override
    public BookDTO updateBook(BookDTO book, Long bookId) {
        return null;
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        return null;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return null;
    }

    @Override
    public void deleteBook(Long bookId) {

    }

    @Override
    public List<Book> getBooksByAuthor(Long authorId) {
        return null;
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return null;
    }

    public Book dtoToBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        return book;
    }

    public BookDTO bookToDTO(Book book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }
}
