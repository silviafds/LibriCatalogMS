package com.libri.catalog.adapters.in.web.dto.request;

public record BookRequest(
        Long id,
        String title,
        String description,
        String author,
        String synopsis,
        Integer pageCount,
        String publisher,
        String type
) {
    // Validações podem ser feitas no construtor compacto
    public BookRequest {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
    }
}