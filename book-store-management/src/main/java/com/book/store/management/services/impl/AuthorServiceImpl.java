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
        Author author = dtoToAuthor(authorDto);
        Author savedAuthor = authorRepo.save(author);
        return authorToDto(savedAuthor);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDto, Long authorId) {
        Author foundedAuthor = authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        foundedAuthor.setName(authorDto.getName());
        foundedAuthor.setEmail(authorDto.getEmail());
        foundedAuthor.setBiography(authorDto.getBiography());

        Author updatedAuthor = authorRepo.save(foundedAuthor);
        return authorToDto(updatedAuthor);
    }

    @Override
    public AuthorDTO getAuthorById(Long authorId) {
        Author foundedAuthor = authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        return authorToDto(foundedAuthor);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepo.findAll();
        List<AuthorDTO> authorDTOS = authors.stream().map(author -> authorToDto(author)).collect(Collectors.toList());
        return authorDTOS;
    }

    @Override
    public void deleteAuthor(Long authorId) {
       Author foundedAuthor = authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
       authorRepo.delete(foundedAuthor);
    }

    public Author dtoToAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        return author;
    }

    public AuthorDTO authorToDto(Author author) {
        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
        return authorDTO;
    }
}
