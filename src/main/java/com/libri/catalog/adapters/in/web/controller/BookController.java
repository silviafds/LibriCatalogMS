package com.libri.catalog.adapters.in.web.controller;

import com.libri.catalog.adapters.in.web.dto.request.BookRequest;
import com.libri.catalog.adapters.in.web.dto.response.BookRegistrationResponse;
import com.libri.catalog.adapters.in.web.dto.response.BookResponse;
import com.libri.catalog.application.mapper.BookMapper;
import com.libri.catalog.application.ports.in.service.BookService;
import com.libri.catalog.domain.vo.BookVo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping("/register-book")
    public BookRegistrationResponse createBook(
            @Valid @RequestBody BookRequest request) {

        BookVo bookVo = bookMapper.toBookVo(request);

        return bookService.registerBook(bookVo);
    }

    @DeleteMapping("/delete-book/{id}")
    public BookRegistrationResponse deleteBook(
            @PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/list-books")
    public List<BookResponse> listAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/list-book/{id}")
    public BookResponse listBookForId(@PathVariable Long id) {
        return bookService.listBookForId(id);
    }

    @PatchMapping("/edit-book")
    public BookResponse partialUpdateBook(
            @RequestBody BookRequest request) {

        BookVo bookVo = bookMapper.toBookVoWithId(request);

        return bookService.partialUpdate(bookVo);
    }

    @GetMapping("/search-book/{title}")
    public BookResponse searchBookByTitle(
            @PathVariable String title) {

        return bookService.searchBookByTitle(title);
    }
}
