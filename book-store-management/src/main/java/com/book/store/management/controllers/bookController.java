package com.book.store.management.controllers;

import com.book.store.management.config.AppConstants;
import com.book.store.management.payloads.ApiResponse;
import com.book.store.management.payloads.BookDAO;
import com.book.store.management.services.AuthorService;
import com.book.store.management.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class bookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<BookDAO>> getAllBooks(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortType", defaultValue = AppConstants.SORT_DIR, required = false) String sortType
    ) {
        return ResponseEntity.ok(this.bookService.getAllBooks(pageNumber, pageSize, sortBy, sortType));
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<BookDAO> getBookById(@PathVariable Long authorId) {
        BookDAO foundedBook = this.bookService.getBookById(authorId);
        return new ResponseEntity<>(foundedBook, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDAO> createBook(@Valid @RequestBody BookDAO bookDAO) {
        BookDAO createdBook = this.bookService.createBook(bookDAO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDAO> updateBook(@Valid @RequestBody BookDAO bookDto, @PathVariable("bookId") Long bookId) {
        BookDAO updatedBook = this.bookService.updateBook(bookDto, bookId);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> removeBook(@PathVariable("bookId") Long bookId) {
        this.bookService.deleteBook(bookId);
        return new ResponseEntity<>(new ApiResponse("Book Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/ByAuthorId/{authorId}")
    public ResponseEntity<List<BookDAO>> getBookByAuthorId(@PathVariable Long authorId) {
        List<BookDAO> foundedBooks = this.bookService.getBooksByAuthor(authorId);
        return new ResponseEntity<List<BookDAO>>(foundedBooks, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDAO>> searchBookByTitle(@RequestParam(name = "title", required = true) String keyWords) {
        List<BookDAO> books = this.bookService.searchBooks(keyWords);
        return new ResponseEntity<List<BookDAO>>(books, HttpStatus.OK);
    }

}
