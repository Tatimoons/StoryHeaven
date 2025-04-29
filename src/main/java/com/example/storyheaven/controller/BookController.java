package com.example.storyheaven.controller;

import com.example.storyheaven.entity.Book;
import com.example.storyheaven.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    // === Доступно всем: список всех книг ===
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    // === Только для администратора: список книг с возможностью CRUD ===
    @GetMapping("/admin/books")
    public String adminBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "admin/books";
    }

    @GetMapping("/admin/books/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/book-form";
    }

    @PostMapping("/admin/books/save")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/books/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "admin/book-form";
    }

    @GetMapping("/admin/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/admin/books";
    }
}
