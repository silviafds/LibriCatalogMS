package com.libri.catalog.application.mapper;

import com.libri.catalog.adapters.in.web.dto.request.BookRequest;
import com.libri.catalog.adapters.in.web.dto.response.BookResponse;
import com.libri.catalog.domain.model.Book;
import com.libri.catalog.domain.vo.BookVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BookMapper {

    public BookVo toBookVo(BookRequest request) {
        return BookVo.builder()
                .title(request.title())
                .description(request.description())
                .author(request.author())
                .synopsis(request.synopsis())
                .pageCount(request.pageCount())
                .publisher(request.publisher())
                .type(request.type())
                .build();
    }

    public BookVo toBookVoWithId(BookRequest request) {
        return BookVo.builder()
                .id(request.id())
                .title(request.title())
                .description(request.description())
                .author(request.author())
                .synopsis(request.synopsis())
                .pageCount(request.pageCount())
                .publisher(request.publisher())
                .type(request.type())
                .build();
    }

    public Book toBook(BookVo vo) {
        return Book.builder()
                .title(vo.getTitle())
                .description(vo.getDescription())
                .author(vo.getAuthor())
                .synopsis(vo.getSynopsis())
                .pageCount(vo.getPageCount())
                .publisher(vo.getPublisher())
                .type(vo.getType())
                .build();
    }

    public List<BookResponse> bookListToBookResponseList(List<Book> books) {
        if (books == null) {
            return Collections.emptyList();
        }

        List<BookResponse> responses = new ArrayList<>();

        for (Book book : books) {
            BookResponse response = BookResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .author(book.getAuthor())
                    .synopsis(book.getSynopsis())
                    .pageCount(book.getPageCount())
                    .publisher(book.getPublisher())
                    .type(book.getType())
                    .build();
            responses.add(response);
        }

        return responses;
    }

    public BookResponse bookToBookResponse(Book book) {
        BookResponse response = BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .author(book.getAuthor())
                .synopsis(book.getSynopsis())
                .pageCount(book.getPageCount())
                .publisher(book.getPublisher())
                .type(book.getType())
                .build();

        return response;
    }

}