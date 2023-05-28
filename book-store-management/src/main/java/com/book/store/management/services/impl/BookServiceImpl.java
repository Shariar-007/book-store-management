package com.book.store.management.services.impl;

import com.book.store.management.entity.Author;
import com.book.store.management.entity.Book;
import com.book.store.management.exceptions.ResourceNotFoundException;
import com.book.store.management.payloads.BookDAO;
import com.book.store.management.repositories.AuthorRepo;
import com.book.store.management.repositories.BookRepo;
import com.book.store.management.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDAO createBook(BookDAO bookDao) {
        Book book = this.modelMapper.map(bookDao, Book.class);
        Book savedBook = this.bookRepo.save(book);
        return this.modelMapper.map(savedBook, BookDAO.class);
    }

    @Override
    public BookDAO updateBook(BookDAO bookDao, Long bookId) {
        Book foundedBook = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        foundedBook.setTitle(bookDao.getTitle());
        foundedBook.setISBN(bookDao.getISBN());
        List<Author> authors = this.authorRepo.findAllById(bookDao.getAuthors());
        foundedBook.setAuthors(authors);
        Book updatedBook = this.bookRepo.save(foundedBook);
        return this.modelMapper.map(updatedBook, BookDAO.class);
    }

    @Override
    public BookDAO getBookById(Long bookId) {
        Book foundedBook = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        return this.modelMapper.map(foundedBook, BookDAO.class);
    }

    @Override
    public List<BookDAO> getAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        Sort sort = null;
        if(sortType.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        } else if (sortType.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Book> pagedAuthor = this.bookRepo.findAll(pageable);

        List<Book> authors = pagedAuthor.getContent();
        List<BookDAO> bookDAOS = authors.stream().map(book -> this.modelMapper.map(book, BookDAO.class)).collect(Collectors.toList());
        return bookDAOS;
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = this.bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        this.bookRepo.delete(book);
    }

    @Override
    public List<BookDAO> getBooksByAuthor(Long authorId) {
        Author author = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
        List<Book> books = this.bookRepo.findByAuthor(author);
        List<BookDAO> bookDAOS =  books.stream().map((book) -> this.modelMapper.map(book, BookDAO.class)).collect(Collectors.toList());
        return bookDAOS;
    }

    @Override
    public List<BookDAO> searchBooks(String title) {
        List<Book> books = this.bookRepo.findByTitleContainingIgnoreCase(title);
        List<BookDAO> bookDAOS =  books.stream().map((book) -> modelMapper.map(book, BookDAO.class)).collect(Collectors.toList());
        return bookDAOS;
    }
}
