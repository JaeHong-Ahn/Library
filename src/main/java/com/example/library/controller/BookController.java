package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/books")
//    public String list(Model model){
//        List<Book> books = bookService.findBooks();
//        model.addAttribute("books", books);
//        return "home";
//    }

//    @GetMapping("/books/search")
//    public String book(Model model, String title){
//        List<Book> books = bookService.findTitle(title);
//        model.addAttribute("books", books);
//        return "books/searchContent";
//    }

//    @GetMapping("/books/delete")
//    public String deleteBook(){
//        return "books/deleteContent";
//    }

    @PostMapping("/books/delete")
    public String delete(Model model, @Param("id") Long id){
        List<Book> books = bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/books/fix")
    public String fixBook1(Model model, @Param("title") Optional<String> title){
        if(title.isPresent()){ // title이 null값이 아니면
            List<Book> books = bookService.findTitle(String.valueOf(title));
            model.addAttribute("books", books);
        } else {
            List<Book> books = bookService.findBooks();
            model.addAttribute("books", books);
        }
        return "books/fixList";
    }

    @PostMapping("/books/fix")
    public String fixBook2(String title, Model model){
        List<Book> books = bookService.findTitle(title);
        model.addAttribute("books", books);
        return "books/fixContent";
    }

    @PostMapping("/books/fixing")
    public String modify(Model model, String title){



//        return "redirect:/books/fixList";
        return "redirect:/";
    }

//    @PostMapping("/books/fixing")
//    public String fixBooks2(BookForm form, Book book){
//        System.out.println("fixBooks2 PostMapping 호출");
//
//        book.setTitle(form.getTitle());
//        book.setWriter(form.getWriter());
//        book.setYear(form.getYear());
//
//        return "redirect:/";
//    }
}
