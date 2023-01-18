package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/new")
    public String createForm(){
        return "books/addContent";
    }

    @PostMapping("/books/new")
    public String create(BookForm form){
        Book book = new Book();
        book.setTitle(form.getTitle());

        bookService.join(book);

        return "redirect:/";
    }

    @GetMapping("/books")
    public String list(Model model){
        List<Book> books = bookService.findMembers();
        model.addAttribute("books", books);
        return "books/bookList";
    }
}
