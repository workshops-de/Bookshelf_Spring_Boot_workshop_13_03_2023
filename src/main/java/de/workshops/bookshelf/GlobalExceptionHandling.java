package de.workshops.bookshelf;

import de.workshops.bookshelf.book.BookException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(BookException.class)
    ResponseEntity<String> handleBookException(BookException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleViolationExceptions(ConstraintViolationException e) {
        return ResponseEntity.badRequest().build();
    }
}
