package com.example.book_shop;

public record BookSearchFilter(
        Integer pageSize,
        Integer pageNumber
) {
}
