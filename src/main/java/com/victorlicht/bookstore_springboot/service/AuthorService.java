package com.victorlicht.bookstore_springboot.service;

import com.victorlicht.bookstore_springboot.model.Author;
import com.victorlicht.bookstore_springboot.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findById(Long id) {
            return authorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author insert(Author author) {
        if (author.getId() != null) {
            throw new IllegalArgumentException("Author ID must be null for insert.");
        }
        return authorRepository.save(author);
    }

    public Author update(Author author) {
        Author existingAuthor = findById(author.getId());
        existingAuthor.setName(author.getName());
        return authorRepository.save(existingAuthor);
    }

    public void deleteById(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Author not found with ID: " + id);
        }
    }
}
