package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BookRestControllerAddBookTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void shouldAddBookToShelf() throws Exception {
        String myBook = """
                {
                		"title": "My second book",
                		"description": "Wish I had written it much earlier",
                		"author": "Birgit Kratz",
                		"isbn": "111-1111111"
                }""";
        mockMvc.perform(post("/book").content(myBook))
                .andExpect(status().isCreated());
    }
}
