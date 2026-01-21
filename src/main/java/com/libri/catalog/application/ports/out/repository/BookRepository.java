package com.libri.catalog.application.ports.out.repository;

import com.libri.catalog.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("UPDATE Book b SET " +
            "b.title = :title, " +
            "b.author = :author, " +
            "b.description = :description, " +
            "b.synopsis = :synopsis, " +
            "b.pageCount = :pageCount, " +
            "b.publisher = :publisher, " +
            "b.type = :type " +
            "WHERE b.id = :id")
    int updateBook(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("author") String author,
            @Param("description") String description,
            @Param("synopsis") String synopsis,
            @Param("pageCount") Integer pageCount,
            @Param("publisher") String publisher,
            @Param("type") String type);

    @Query(value = "SELECT * FROM books WHERE title = :title", nativeQuery = true)
    Book searchBookByTitle(@Param("title") String title);

}