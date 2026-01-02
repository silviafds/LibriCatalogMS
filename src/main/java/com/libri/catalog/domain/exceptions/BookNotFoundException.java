package com.libri.catalog.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Livro com ID " + id + " não encontrado.");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
