package com.example.book_shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private StoryEntity story;

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "year_release")
    private Year year;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;
}
