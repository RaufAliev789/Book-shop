package com.example.book_shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper mapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    public List<Book> searchAllBooksByFilter(Long authorId, BookSearchFilter filter){

        int pageSize = filter.pageSize() != null
                ? filter.pageSize() : 10;
        int pageNumber = filter.pageNumber() != null
                ? filter.pageNumber() : 0;

        var pageable = Pageable
                .ofSize(pageSize)
                .withPage(pageNumber);

        if (!authorRepository.existsById(authorId)){
            throw new IllegalArgumentException("This authorId does not exist. AuthorId = " + authorId);
        }

        List<BookEntity> allEntities = bookRepository.searchAllByFilter(
                authorId,
                pageable
                );

        return allEntities.stream()
                .map(mapper :: toDomain)
                .toList();
    }

}
