package de.workshops.bookshelf.book;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookRestControllerRestAssuredTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestControllerRestAssuredTest.class);

    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {
        RestAssured.port = port;
    }

    @Test
    void shouldGetAllBooks() {
        LOGGER.info("shouldGetAllBooks Port {}", RestAssured.port);
        final var allBooks = RestAssured
                .given()
                .auth().basic("dbUser", "password")
                .when().get("/book")
                .then().statusCode(HttpStatus.OK.value())
                .extract().body().as(new TypeRef<List<Book>>() {});

        assertThat(allBooks).hasSize(3)
                .extracting("title")
                .containsExactlyInAnyOrder("Design Patterns", "Clean Code", "Coding for Fun");
    }

    @Test
    void shouldGetByIsbn() {
        LOGGER.info("shouldGetByIsbn Port {}", port);
        final var book = RestAssured
                .given()
                .auth().basic("dbUser", "password")
                .when().get("/book/978-3826655487")
                .then().statusCode(HttpStatus.OK.value())
                .extract().body().as(new TypeRef<Book>() {});

        assertThat(book).isNotNull()
                .hasFieldOrPropertyWithValue("title", "Clean Code");
    }
}
