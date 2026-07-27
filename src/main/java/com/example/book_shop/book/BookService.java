package com.example.book_shop.book;
import com.example.book_shop.author.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
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
            throw new EntityNotFoundException("Not found author by id = " + authorId);
        }

        List<BookEntity> allEntities = bookRepository.searchAllByFilter(
                authorId,
                pageable
                );

        return allEntities.stream()
                .map(bookMapper :: toDomain)
                .toList();
    }

    public Book reserveBookById(Long id) {
        var bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found book with id = " + id
                ));

        if (bookEntity.getStatus() != BookStatus.AVAILABLE){
            throw new IllegalStateException("Cannot reserve book with the status - " + bookEntity.getStatus());
        }

        bookEntity.setStatus(BookStatus.RESERVED);

        bookRepository.save(bookEntity);

        return bookMapper.toDomain(bookEntity);
    }
}
