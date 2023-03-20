package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers={BookRestController.class})
class BookRestControllerAddBookTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    @WithMockUser
    void shouldAddBookToShelf() throws Exception {
        String myBook = """
                {
                		"title": "My second book",
                		"description": "Wish I had written it much earlier",
                		"author": "Birgit Kratz",
                		"isbn": "111-1111111"
                }""";
        mockMvc.perform(post("/book").content(myBook).with(csrf()))
                .andExpect(status().isCreated());
    }
}
