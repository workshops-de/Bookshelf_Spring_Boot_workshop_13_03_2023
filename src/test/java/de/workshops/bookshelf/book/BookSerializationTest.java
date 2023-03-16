package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.workshops.bookshelf.ObjectMapperTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(ObjectMapperTestConfiguration.class)
public class BookSerializationTest {

    @Autowired
    ObjectMapper mapper;

    @Test
    void shouldSerializeWithIdents() throws JsonProcessingException {
        final var book = new Book();
        book.setAuthor("Me");
        book.setIsbn("1111111");
        book.setTitle("Hello World");

        final var s = mapper.writeValueAsString(book);
        assertThat(s).isNotBlank();
        System.out.println(s);
    }
}
