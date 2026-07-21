package com.example.book_shop.book;

public record BookSearchFilter(
        Integer pageSize,
        Integer pageNumber
) {
}
