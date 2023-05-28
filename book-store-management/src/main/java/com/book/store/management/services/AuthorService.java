package com.book.store.management.services;

import com.book.store.management.payloads.AuthorDAO;

import java.util.List;

public interface AuthorService {

    AuthorDAO createAuthor(AuthorDAO author);

    AuthorDAO updateAuthor(AuthorDAO author, Long authorId);

    AuthorDAO getAuthorById(Long authorId);

    List<AuthorDAO> getAllAuthors(Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    void deleteAuthor(Long authorId);
}
