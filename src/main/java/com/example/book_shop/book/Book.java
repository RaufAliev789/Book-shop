package com.example.book_shop.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Null
    private Long bookId;

    @NotNull
    private Long storyId;

    @NotNull
    private String ISBN;

    @NotNull
    private String publisher;

    @NotNull
    private Year year;

    @NotNull
    private int price;

    @NotNull
    private BookStatus status;
}
