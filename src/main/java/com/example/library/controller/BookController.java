package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/new")
    public String createForm() {
        return "books/addContent";
    }

    @PostMapping("/books/new")
    public String create(BookForm form) {
        Book book = new Book();

        book.setTitle(form.getTitle());
        book.setWriter(form.getWriter());
        book.setYear(form.getYear());

        bookService.join(book);

        return "redirect:/";
    }

    @PostMapping("/books/delete")
    public String delete(@Param("id") Long id) {
        List<Book> books = bookService.deleteById(id);
        return "redirect:/";
    }



    @GetMapping("/books/fix")
    public String fixBook2(@Param("id") Long id, Model model) {
        Book book = bookService.findOne(id).get();
        model.addAttribute("book", book);

        return "books/fixContent";
    }

    @PostMapping("/books/fix")
    public String fixBook3(BookForm form, @RequestParam("id") Long id) {

        Book book = bookService.findOne(id).get();

        book.setTitle(form.getTitle());
        book.setWriter(form.getWriter());
        book.setYear(form.getYear());

        bookService.join(book);

        return "redirect:/";
    }
}