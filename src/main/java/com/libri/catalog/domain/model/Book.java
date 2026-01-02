package com.libri.catalog.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "synopsis", nullable = false, length = 200)
    private String synopsis;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "type", length = 100)
    private String type;


}