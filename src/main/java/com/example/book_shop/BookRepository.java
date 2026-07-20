package com.example.book_shop;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query(value = """
            SELECT b FROM BookEntity b
            JOIN b.story s
            JOIN s.author a
            WHERE (a.authorId = :authorId)
            """)
    List<BookEntity> searchAllByFilter(
            @Param("authorId") Long authorId,
            Pageable pageable
    );

}
