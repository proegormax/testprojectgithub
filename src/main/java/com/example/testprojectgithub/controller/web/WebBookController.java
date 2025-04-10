package com.example.testprojectgithub.controller.web;

import com.example.testprojectgithub.dto.BookDto;
import com.example.testprojectgithub.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")

public class WebBookController {

    private final BookService bookService;

    public WebBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "books/form";
    }

    @PostMapping
    public String createBook(@ModelAttribute BookDto book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        BookDto book = bookService.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        model.addAttribute("book", book);
        return "books/form";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookDto book) {
        bookService.update(id, book);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping
    public String listBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer publishYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        Page<BookDto> books = bookService.findAll(title, brand, publishYear, PageRequest.of(page, size));
        model.addAttribute("books", books);
        model.addAttribute("title", title);
        model.addAttribute("brand", brand);
        model.addAttribute("publishYear",publishYear);
        return "books/list";
    }

}
