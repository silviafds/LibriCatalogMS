package com.libri.catalog.adapters.in.web.controller;

import com.libri.catalog.adapters.in.web.dto.request.BookRequest;
import com.libri.catalog.adapters.in.web.dto.response.BookRegistrationResponse;
import com.libri.catalog.application.mapper.BookMapper;
import com.libri.catalog.application.ports.in.service.BookService;
import com.libri.catalog.domain.vo.BookVo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
}
