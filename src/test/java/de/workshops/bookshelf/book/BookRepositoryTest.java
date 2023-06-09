package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest(includeFilters = @ComponentScan.Filter(
//        type = FilterType.ANNOTATION,
//        classes = Repository.class
//))
@DataJpaTest
@Import(BookRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    void shouldFindAllBooks() {
        final var allBooks = repository.findAll();
        assertThat(allBooks).hasSize(3);
    }
}