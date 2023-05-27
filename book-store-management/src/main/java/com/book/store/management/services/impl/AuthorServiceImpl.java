package com.book.store.management.services.impl;

import com.book.store.management.entity.Author;
import com.book.store.management.exceptions.ResourceNotFoundException;
import com.book.store.management.payloads.AuthorDTO;
import com.book.store.management.repositories.AuthorRepo;
import com.book.store.management.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDto) {
        Author author = this.dtoToAuthor(authorDto);
        Author savedAuthor = this.authorRepo.save(author);
        return this.authorToDto(savedAuthor);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDto, Long authorId) {
        Author foundedAuthor = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        foundedAuthor.setName(authorDto.getName());
        foundedAuthor.setEmail(authorDto.getEmail());
        foundedAuthor.setBiography(authorDto.getBiography());

        Author updatedAuthor = this.authorRepo.save(foundedAuthor);
        return this.authorToDto(updatedAuthor);
    }

    @Override
    public AuthorDTO getAuthorById(Long authorId) {
        Author foundedAuthor = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        return this.authorToDto(foundedAuthor);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = this.authorRepo.findAll();
        List<AuthorDTO> authorDTOS = authors.stream().map(author -> this.authorToDto(author)).collect(Collectors.toList());
        return authorDTOS;
    }

    @Override
    public void deleteAuthor(Long authorId) {
       Author foundedAuthor = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        this.authorRepo.delete(foundedAuthor);
    }

    public Author dtoToAuthor(AuthorDTO authorDTO) {
        Author author = this.modelMapper.map(authorDTO, Author.class);
        return author;
    }

    public AuthorDTO authorToDto(Author author) {
        AuthorDTO authorDTO = this.modelMapper.map(author, AuthorDTO.class);
        return authorDTO;
    }
}
