package com.example.book_shop;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);

    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    public Author getInfoOfAuthorById(Long id) {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found author by id = " + id
                ));

        return mapper.toDomain(authorEntity);
    }
}
