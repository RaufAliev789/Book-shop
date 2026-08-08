package com.example.book_shop.author;

import com.example.book_shop.book.BookStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void getInfoOfAuthorById_shouldGetInfoOfAuthor_whenIdExist(){
        // Arrange
        Long authorId = 1L;

        AuthorEntity authorEntity = new AuthorEntity(1L, "Александр", "Пушкин", LocalDate.of(1800,9,9));
        // Ожидание
        Author expectedAuthor = new Author(1L, "Александр", "Пушкин", LocalDate.of(1800,9,9));

        // моки
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(authorEntity));
        when(authorMapper.toDomain(authorEntity)).thenReturn(expectedAuthor);

        // Act
        Author result = authorService.getInfoOfAuthorById(authorId);

        // Assert
        assertEquals(expectedAuthor, result);
    }

    @Test
    void getInfoOfAuthorById_shouldThrowException_whenAuthorIdNotFound(){
        // Arrange
        Long authorId = 999L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        // Act + Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> authorService.getInfoOfAuthorById(authorId)
        );

        assertEquals("Not found author by id = 999", exception.getMessage());
    }
}
