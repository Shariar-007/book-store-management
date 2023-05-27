package com.book.store.management.services;

import com.book.store.management.payloads.AuthorDTO;
import com.book.store.management.payloads.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    AuthorDTO createAuthor(AuthorDTO author);

    AuthorDTO updateAuthor(AuthorDTO author, Long authorId);

    AuthorDTO getAuthorById(Long authorId);

    List<AuthorDTO> getAllAuthors(Integer pageNumber, Integer pageSize, String sortBy, String sortType);

    void deleteAuthor(Long authorId);
}
