package de.workshops.bookshelf.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BookService {

    private final BookJpaRepository bookRepository;

    BookService(BookJpaRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    Book getBookByIsbn(String isbn) {
        final var books = bookRepository.findAll();
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    Book getByAuthor(String authorName) {
        final var books = bookRepository.findAll();
        return books.stream()
                .filter(book -> book.getAuthor().startsWith(authorName))
                .findFirst()
                .orElseThrow(() ->new BookException("Kein Buch für diesen Autor gefunden."));
    }

    List<Book> findBooks(BookSearchRequest searchRequest) {
        final var books = bookRepository.findAll();
        return books.stream()
                .filter(book -> book.getAuthor().startsWith(searchRequest.author())
                        || book.getTitle().startsWith(searchRequest.title()))
                .toList();
    }

    Book addBook(Book newBook) {
        if (getBookByIsbn(newBook.getIsbn()) != null) {
            throw new BookException("Dieses Buch steht schon im Regal");
        }
        return bookRepository.save(newBook);
    }
}
