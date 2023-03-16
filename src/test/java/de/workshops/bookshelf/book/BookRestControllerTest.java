package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRestControllerTest {

    @Autowired
    private BookRestController controller;

    @Test
    void shouldGetAllBooks() {
        final var allBooks = controller.getAllBooks();


        assertThat(allBooks).hasSize(3)
                .extracting("title")
                .containsExactlyInAnyOrder("Design Patterns", "Clean Code", "Coding for Fun");
    }
}