package com.example.library.repository;

import com.example.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findByTitle(String title);
    List<Book> findAll();
    List<Book> getFixByTitle(Book book);

    List<Book> deleteById(Long id);
}