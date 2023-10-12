package com.victorlicht.bookstore_springboot.service;

import com.victorlicht.bookstore_springboot.model.Book;
import com.victorlicht.bookstore_springboot.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book Not Found with ID: " + id));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book insert(Book book) {
        if (book.getId() != null) {
            throw new IllegalArgumentException("Book ID must be null");
        }
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        Book existingBook = findById(book.getId());
        existingBook.setName(book.getName());
        return bookRepository.save(existingBook);
    }

    public void deleteById(String id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Author not found with ID: " + id);
        }
    }
}
