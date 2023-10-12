package com.victorlicht.bookstore_springboot.repository;

import com.victorlicht.bookstore_springboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}
