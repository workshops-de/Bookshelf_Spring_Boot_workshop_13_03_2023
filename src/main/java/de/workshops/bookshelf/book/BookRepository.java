package de.workshops.bookshelf.book;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BookRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    BookRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    List<Book> findAll() {
        String sql = "select * from book";
        var books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    Book save(Book book) {
        String sql = "INSERT INTO book (isbn, title, author, description) VALUES (?, ?, ?, ?)";
        final var update = jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getAuthor(), book.getDescription());
        return book;
    }

    Book saveBookWithNamedQuery(Book book) {
        String sql = "INSERT INTO book (isbn, title, author, description) VALUES (:isbn, :title, :author, :description)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("isbn", book.getIsbn())
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("description", book.getDescription());
        final var update = namedParameterJdbcTemplate.update(sql, namedParameters);
        return book;
    }
}
