package com.example.library.repository;

import com.example.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaBookRepository extends JpaRepository<Book, Long>, BookRepository {

    @Override
    Optional<Book> findByTitle(String title);
}
