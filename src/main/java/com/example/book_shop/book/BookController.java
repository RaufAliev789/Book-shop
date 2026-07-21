package com.example.book_shop.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/authors/{id}/books")
    public ResponseEntity<List<Book>> getAllBooksByAuthorId(
        @PathVariable Long id,
        @RequestParam(name = "pageSize", required = false) Integer pageSize,
        @RequestParam(name = "pageNumber", required = false) Integer pageNumber
    ){
        log.info("The method 'getAllBooksByAuthorId' started");
        var filter = new BookSearchFilter(
                pageSize,
                pageNumber
        );

        var book = bookService.searchAllBooksByFilter(id, filter);
        log.info("The method 'getAllBooksByAuthorId' finished successfully");
        return ResponseEntity.status(HttpStatus.OK)
                .body(book);
    }

    @PostMapping("/books/{id}/reserve")
    public ResponseEntity<Book> reserveBookById(@PathVariable Long id){
        log.info("The method reserveBookById started");
        var bookReserve = bookService.reserveBookById(id);
        log.info("The method reserveBookById finished successfully");
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookReserve);
    }


}
