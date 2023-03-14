package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
class BookRepository {
    private final ObjectMapper mapper;
    private final ResourceLoader resourceLoader;

    private List<Book> books;

    public BookRepository(ObjectMapper mapper, ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    void initBookList() throws IOException {
        final var resource = resourceLoader.getResource("classpath:books.json");
        books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    List<Book> findAllBooks() {
        return books;
    }
}
