package com.libri.catalog.application.mapper;

import com.libri.catalog.adapters.in.web.dto.request.BookRequest;
import com.libri.catalog.domain.model.Book;
import com.libri.catalog.domain.vo.BookVo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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



}