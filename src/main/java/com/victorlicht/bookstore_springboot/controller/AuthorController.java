package com.victorlicht.bookstore_springboot.controller;

import com.victorlicht.bookstore_springboot.model.Author;
import com.victorlicht.bookstore_springboot.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        Author author = authorService.findById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        List<Author> authors = authorService.findAll();
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Author> insert(@RequestBody Author author) {
        Author insertedAuthor = authorService.insert(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id); // Make sure the ID is set correctly
        Author updatedAuthor = authorService.update(author);
        if (updatedAuthor != null) {
            return ResponseEntity.ok(updatedAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
