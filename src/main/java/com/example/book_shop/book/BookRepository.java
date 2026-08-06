package com.example.book_shop.book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE BookEntity b
            SET b.status = :newStatus
            WHERE (b.bookId = :id) 
                   AND (b.status = :expectedStatus)
            """)
    int updateStatusIfCurrent(
            @Param("id") Long id,
            @Param("expectedStatus") BookStatus expectedStatus,
            @Param("newStatus") BookStatus newStatus
    );

}
