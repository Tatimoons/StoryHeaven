package com.example.storyheaven.controller;

import com.example.storyheaven.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookRepository bookRepository;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "home";
    }

    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }
}
