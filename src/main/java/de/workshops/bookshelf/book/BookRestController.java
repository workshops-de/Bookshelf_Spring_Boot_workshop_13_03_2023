package de.workshops.bookshelf.book;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    String getAllBooksAsString() {
        return bookService.getAllBooks().toString();
    }

    @GetMapping("{isbn}")
    ResponseEntity<Book> getByIsbn(@PathVariable @Size(min = 10, max = 14) String isbn) {
        final var book = bookService.getBookByIsbn(isbn);

        if (book == null) {
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(book);
    }
    @GetMapping(params="author")
    Book getByAuthor(@RequestParam @Size(min = 3) String author) {
        return bookService.getByAuthor(author);
    }

    @PostMapping("/search")
    List<Book> search(@RequestBody @Valid BookSearchRequest searchRequest) {
        return bookService.findBooks(searchRequest);
    }

    @ExceptionHandler(BookException.class)
    ResponseEntity<String> handleBookException(BookException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }
}
