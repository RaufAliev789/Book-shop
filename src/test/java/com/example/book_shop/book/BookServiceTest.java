package com.example.book_shop.book;

import com.example.book_shop.author.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void reserveBookById_shouldReserveBook_whenStatusIsAvailable(){
        //Arrange
        Long bookId = 1L;

        //"исходные данные"
        BookEntity bookEntity = new BookEntity(
                bookId, null, "978-5-389-08846-7", "Азбука", Year.of(2023), 350, BookStatus.AVAILABLE
        );

        // Ожидание
        Book expectedBook = new Book(
                bookId, null, "978-5-389-08846-7", "Азбука", Year.of(2023), 350, BookStatus.RESERVED
        );

        // Прог-е моков
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
        when(bookRepository.updateStatusIfCurrent(bookId, BookStatus.AVAILABLE, BookStatus.RESERVED))
                .thenReturn(1);
        when(bookMapper.toDomain(bookEntity)).thenReturn(expectedBook);

        // Act
        Book result = bookService.reserveBookById(bookId);

        // Assert
        assertEquals(BookStatus.RESERVED, result.getStatus());
        assertEquals(bookId, result.getBookId());

        verify(bookRepository).updateStatusIfCurrent(bookId, BookStatus.AVAILABLE, BookStatus.RESERVED);
    }

    @Test
    void reserveBookById_shouldThrowException_whenBookAlreadyReserved(){
        // Arrange
        Long bookId = 2L;
        BookEntity bookEntity = new BookEntity(
                bookId, null, "978-5-04-181594-3", "Эксмо", Year.of(2024), 700, BookStatus.RESERVED
        );

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
        when(bookRepository.updateStatusIfCurrent(bookId, BookStatus.AVAILABLE, BookStatus.RESERVED))
                .thenReturn(0);

        // Act + Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> bookService.reserveBookById(bookId)
        );

        assertEquals("Cannot reserve book with the status - RESERVED", exception.getMessage());
    }

    @Test
    void reserveBookById_shouldThrowException_whenBookNotFound(){
        // Arrange
        Long bookId = 999L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act + Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> bookService.reserveBookById(bookId)
        );

        assertEquals("Not found book with id = 999", exception.getMessage());
        verify(bookRepository, never()).updateStatusIfCurrent(any(), any(), any());
    }

    @Test
    void searchAllBooksByFilter_shouldUseDefaultPagination_whenFilterValuesAreNull() {
        // Arrange
        Long authorId = 1L;
        BookSearchFilter filter = new BookSearchFilter(null, null);

        BookEntity bookEntity = new BookEntity(
                1L, null, "978-5-389-08846-7", "Азбука", Year.of(2023), 350, BookStatus.AVAILABLE
        );
        Book expectedBook = new Book(
                1L, null, "978-5-389-08846-7", "Азбука", Year.of(2023), 350, BookStatus.AVAILABLE
        );

        when(authorRepository.existsById(authorId)).thenReturn(true);
        when(bookRepository.searchAllByFilter(authorId, PageRequest.of(0, 10)))
                .thenReturn(List.of(bookEntity));
        when(bookMapper.toDomain(bookEntity)).thenReturn(expectedBook);

        // Act
        List<Book> result = bookService.searchAllBooksByFilter(authorId, filter);

        // Assert
        assertEquals(1, result.size());
        assertEquals(expectedBook, result.get(0));

        verify(bookRepository).searchAllByFilter(authorId, PageRequest.of(0, 10));
    }

    @Test
    void searchAllBooksByFilter_shpouldThrowException_whenAuthorNotFound() {
        // Arrange
        Long authorId = 404L;
        BookSearchFilter filter = new BookSearchFilter(null, null);
        when(authorRepository.existsById(authorId)).thenReturn(false);

        // Act + Assert
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> bookService.searchAllBooksByFilter(authorId, filter)
        );
        assertEquals("Not found author by id = 404", exception.getMessage());
        verify(bookRepository, never()).searchAllByFilter(any(), any());
    }

}
