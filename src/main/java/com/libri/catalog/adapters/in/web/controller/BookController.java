package com.libri.catalog.adapters.in.web.controller;

import com.libri.catalog.adapters.in.web.dto.request.BookRequest;
import com.libri.catalog.adapters.in.web.dto.response.BookRegistrationResponse;
import com.libri.catalog.adapters.in.web.dto.response.BookResponse;
import com.libri.catalog.application.mapper.BookMapper;
import com.libri.catalog.application.ports.in.service.BookService;
import com.libri.catalog.domain.vo.BookVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Register new book",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Book registered in the system"),
                    @ApiResponse(responseCode = "400", description = "Book registration failed"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @PostMapping("/register-book")
    public BookRegistrationResponse createBook(
            @Valid @RequestBody BookRequest request) {

        BookVo bookVo = bookMapper.toBookVo(request);

        return bookService.registerBook(bookVo);
    }

    @Operation(summary = "Delete book",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete book in the system"),
                    @ApiResponse(responseCode = "400", description = "Delete book failed"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @DeleteMapping("/delete-book/{id}")
    public BookRegistrationResponse deleteBook(
            @PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @Operation(summary = "Listing all book",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listing all books in the system"),
                    @ApiResponse(responseCode = "400", description = "Listing failed"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping("/list-books")
    public List<BookResponse> listAllBooks() {
        return bookService.listAllBooks();
    }

    @Operation(summary = "List book for id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listing book in the system for id"),
                    @ApiResponse(responseCode = "400", description = "Listing failed"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping("/list-book/{id}")
    public BookResponse listBookForId(@PathVariable Long id) {
        return bookService.listBookForId(id);
    }

    @Operation(summary = "Edit book",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Edit book in the system for id"),
                    @ApiResponse(responseCode = "400", description = "Edit failed"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @PatchMapping("/edit-book")
    public BookResponse partialUpdateBook(
            @RequestBody BookRequest request) {

        BookVo bookVo = bookMapper.toBookVoWithId(request);

        return bookService.partialUpdate(bookVo);
    }

    @Operation(summary = "Search book for title",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Search book in the system for title"),
                    @ApiResponse(responseCode = "400", description = "Search book failed"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping("/search-book/{title}")
    public BookResponse searchBookByTitle(
            @PathVariable String title) {

        return bookService.searchBookByTitle(title);
    }
}
