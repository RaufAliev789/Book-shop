package com.example.book_shop.author;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author toDomain(AuthorEntity author){
        return new Author(
                author.getAuthorId(),
                author.getFirstname(),
                author.getLastname(),
                author.getBirthday()
        );
    }

    public AuthorEntity toEntity(Author author){
        return new AuthorEntity(
                author.getAuthorId(),
                author.getFirstname(),
                author.getLastname(),
                author.getBirthday()
        );
    }
}
