package com.example.library.repository;

import com.example.library.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaBookRepository implements BookRepository {

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        return null;
    }
}
