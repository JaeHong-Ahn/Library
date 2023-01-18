package com.example.library.service;

import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final BookRepository bookRepository;

    @Autowired
    public SpringConfig(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Bean
    public BookService service() {
        return new BookService(bookRepository);
    }
}
