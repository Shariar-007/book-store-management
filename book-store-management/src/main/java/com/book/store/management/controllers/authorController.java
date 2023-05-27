package com.book.store.management.controllers;

import com.book.store.management.config.AppConstants;
import com.book.store.management.payloads.ApiResponse;
import com.book.store.management.payloads.AuthorDTO;
import com.book.store.management.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class authorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllUser(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortType", defaultValue = AppConstants.SORT_DIR, required = false) String sortType
    ) {
        return ResponseEntity.ok(this.authorService.getAllAuthors(pageNumber, pageSize, sortBy, sortType));
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long authorId) {
        AuthorDTO foundedAuthor = this.authorService.getAuthorById(authorId);
        return new ResponseEntity<>(foundedAuthor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = this.authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO authorDto, @PathVariable("authorId") Long authorId) {
        AuthorDTO updatedAuthor = this.authorService.updateAuthor(authorDto, authorId);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<ApiResponse> removeAuthor(@PathVariable("authorId") Long authorId) {
        this.authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }
}
