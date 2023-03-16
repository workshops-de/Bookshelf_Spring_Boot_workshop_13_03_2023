package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestControllerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    BookRestController bookRestController;

    @Captor
    ArgumentCaptor<String> isbnCaptor;

    @Test
    void shouldGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(List.of());

        final var allBooks = bookRestController.getAllBooks();

        assertThat(allBooks).isEmpty();
    }

    @Test
    void shouldGetByIsbn() {
        when(bookService.getBookByIsbn(isbnCaptor.capture())).thenReturn(new Book());

        bookRestController.getByIsbn("1234567890");

        assertThat(isbnCaptor.getValue()).isEqualTo("1234567890");
        verify(bookService).getBookByIsbn("1234567890");
    }
}
