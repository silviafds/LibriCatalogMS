package com.libri.catalog.application.ports.in.service;

import com.libri.catalog.adapters.in.web.dto.response.BookRegistrationResponse;
import com.libri.catalog.adapters.in.web.dto.response.BookResponse;
import com.libri.catalog.domain.vo.BookVo;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface BookService {

    BookRegistrationResponse registerBook(BookVo vo);
    BookRegistrationResponse deleteBook(Long id);
    List<BookResponse> listAllBooks();
    BookResponse listBookForId(Long id);
    BookResponse partialUpdate(BookVo vo);
    BookResponse searchBookByTitle(String title);
}
