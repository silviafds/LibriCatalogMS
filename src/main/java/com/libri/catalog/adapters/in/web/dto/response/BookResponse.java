package com.libri.catalog.adapters.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String synopsis;
    private Integer pageCount;
    private String publisher;
    private String type;
}
