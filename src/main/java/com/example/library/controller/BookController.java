package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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
        book.setWriter(form.getWriter());
        book.setYear(form.getYear());

        bookService.join(book);

        return "redirect:/";
    }

    @GetMapping("/books")
    public String list(Model model){
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "books/bookList";
    }

    @GetMapping("/books/search")
    public String book(Model model, String title){
        List<Book> books = bookService.findTitle(title);
        model.addAttribute("books", books);
        return "books/searchContent";
    }

//    @DeleteMapping("/delete/{title}")
//    public String delete(@PathVariable Book book, @PathVariable String title){
//        if(book.getTitle().equals(title)){
//
//        }
//    }
}
