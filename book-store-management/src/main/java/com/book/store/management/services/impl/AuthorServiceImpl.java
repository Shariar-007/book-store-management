package com.book.store.management.services.impl;

import com.book.store.management.entity.Author;
import com.book.store.management.exceptions.ResourceNotFoundException;
import com.book.store.management.payloads.AuthorDAO;
import com.book.store.management.repositories.AuthorRepo;
import com.book.store.management.services.AuthorService;
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
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired(required = false)
    private ModelMapper modelMapper;

    @Override
    public AuthorDAO createAuthor(AuthorDAO authorDao) {
        Author author = this.dtoToAuthor(authorDao);
        Author savedAuthor = this.authorRepo.save(author);
        return this.authorToDto(savedAuthor);
    }

    @Override
    public AuthorDAO updateAuthor(AuthorDAO authorDao, Long authorId) {
        Author foundedAuthor = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        foundedAuthor.setName(authorDao.getName());
        foundedAuthor.setEmail(authorDao.getEmail());
        foundedAuthor.setBiography(authorDao.getBiography());

        Author updatedAuthor = this.authorRepo.save(foundedAuthor);
        return this.authorToDto(updatedAuthor);
    }

    @Override
    public AuthorDAO getAuthorById(Long authorId) {
        Author foundedAuthor = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        return this.authorToDto(foundedAuthor);
    }

    @Override
    public List<AuthorDAO> getAllAuthors(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        Sort sort = null;
        if(sortType.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        } else if (sortType.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Author> pagedAuthor = this.authorRepo.findAll(pageable);

        List<Author> authors = pagedAuthor.getContent();
        List<AuthorDAO> authorDAOS = authors.stream().map(author -> this.authorToDto(author)).collect(Collectors.toList());
        return authorDAOS;
    }

    @Override
    public void deleteAuthor(Long authorId) {
       Author foundedAuthor = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "id", authorId));
        this.authorRepo.delete(foundedAuthor);
    }

    public Author dtoToAuthor(AuthorDAO authorDAO) {
        Author author = this.modelMapper.map(authorDAO, Author.class);
        return author;
    }

    public AuthorDAO authorToDto(Author author) {
        AuthorDAO authorDAO = this.modelMapper.map(author, AuthorDAO.class);
        return authorDAO;
    }
}
