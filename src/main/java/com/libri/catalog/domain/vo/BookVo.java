package com.libri.catalog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookVo {
    private String title;
    private String description;
    private String author;
    private String synopsis;
    private Integer pageCount;
    private String publisher;
    private String type;
}
