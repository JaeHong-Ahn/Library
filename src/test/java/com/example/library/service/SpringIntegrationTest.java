package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class SpringIntegrationTest {

    @Autowired BookService bookService;
    @Autowired BookRepository bookRepository;

    @Test
    public void addBook(){

        //given
        Book book = new Book();
        book.setTitle("상실의시대");

        //when
        Long id = bookService.join(book);

        //then
        Book findBook = bookRepository.findById(id).get();
        assertThat(book.getTitle()).isEqualTo(findBook.getTitle());

    }
    @Test
    public void checkDuplication(){

        //given
        Book book1 = new Book();
        book1.setTitle("안재홍");
        book1.setWriter("안재홍2");
        book1.setYear("1998");

        //when
        Book book2 = new Book();
        book2.setTitle("안재홍");
        book2.setWriter("안재홍2");
        book2.setYear("1998");


        //then
        bookService.join(book1);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> bookService.join(book2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("[ERROR] 동일한 제목의 도서가 이미 존재합니다.");

    }
}
