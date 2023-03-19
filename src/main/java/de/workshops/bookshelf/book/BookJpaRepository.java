package de.workshops.bookshelf.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface BookJpaRepository extends JpaRepository<Book, Integer> {
    Book findByIsbn(String isbn);

    @Query("select b from Book b where b.author = :author")
    Book findeByAuthor(String author);
}
