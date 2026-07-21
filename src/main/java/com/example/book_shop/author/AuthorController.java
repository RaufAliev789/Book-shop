package com.example.book_shop.author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthorController {
    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("authors/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id){
        log.info("The method 'getInfoOfAuthorById' started");
        Author getAuthor = authorService.getInfoOfAuthorById(id);
        log.info("The method 'getInfoOfAuthorById' finished successfully");

        return ResponseEntity.status(HttpStatus.OK)
                .body(getAuthor);
    }

}
