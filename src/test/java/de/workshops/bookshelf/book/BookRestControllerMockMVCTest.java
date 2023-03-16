package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerMockMVCTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void shouldGetAllBooks_testWithJsonPath() throws Exception {
        // use static imports
        mockMvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].title", is("Clean Code")));
    }

    @Test
    void shouldGetAllBooks_testWithMVCResult() throws Exception {
        // use static imports
        final var mvcResult = mockMvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var payload = mvcResult.getResponse().getContentAsString();

        List<Book> result = mapper.readValue(payload, new TypeReference<>() {});
        assertThat(result).hasSize(3)
                .extracting("title")
                .containsExactlyInAnyOrder("Design Patterns", "Clean Code", "Coding for Fun");
    }
}