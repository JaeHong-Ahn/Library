package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Long join(Book book){
        bookRepository.save(book);
        return book.getId();
    }

    public List<Book> findBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> findOne(Long bookId){
        return bookRepository.findById(bookId);
    }

    public Book findTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> deleteById(Long id){
        return bookRepository.deleteBookById(id);
    }

    public List<Book> getFixByTitle(Book book){
        return bookRepository.getFixByTitle(book);
    }

}
