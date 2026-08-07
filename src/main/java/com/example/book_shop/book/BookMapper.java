package com.example.book_shop.book;

import com.example.book_shop.story.StoryEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toDomain(BookEntity book){
        return new Book(
                book.getBookId(),
                book.getStory().getStoryId(),
                book.getISBN(),
                book.getPublisher(),
                book.getYear(),
                book.getPrice(),
                book.getStatus()
        );
    }

    public BookEntity toEntity(Book book){
        return new BookEntity(
                book.getBookId(),
                book.getStoryId() != null ? new StoryEntity(book.getStoryId()) : null, //конструктор в родителе
                book.getISBN(),
                book.getPublisher(),
                book.getYear(),
                book.getPrice(),
                book.getStatus()
        );
    }
}
