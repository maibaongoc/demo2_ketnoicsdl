package com.example.demo2;

import com.example.demo2.entity.*;
import org.springframework.ui.Model;
import com.example.demo2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books= bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book/list";

    }

}
