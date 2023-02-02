package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa를 통한 데이터의 변경은 트랜잭션 안에서 실행해야 함!!
public class BookService {

    private final BookRepository bookRepository;
    private static final String DUPLICATED_TITLE = "[ERROR] 동일한 제목의 도서가 이미 존재합니다.";

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Long join(Book book){
        if(bookRepository.findByTitle(book.getTitle()) == null){
            bookRepository.save(book);
        }else {
            throw new IllegalStateException(DUPLICATED_TITLE);
        }
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

}
