package com.libri.catalog.domain.model;

import com.libri.catalog.domain.enums.BookType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20)
    private BookType type;

    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Método auxiliar para pré-salvar
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Método auxiliar para pré-atualizar
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}