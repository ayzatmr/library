package com.study.library.controller;

import com.study.library.config.BaseTestConfig;
import com.study.library.domain.Book;
import com.study.library.util.CreateBook;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//unit test with mocks
public class ControllerTest extends BaseTestConfig {

    @Test
    public void editBookTest() throws Exception {
        Book book = CreateBook.getTestBook();
        when(bookRepository.getBook(1)).thenReturn(book);

        mockMvc.perform(get("/editBook/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("addBook"))
                .andExpect(model().attribute("book",
                        allOf(hasProperty("id", is(1)),
                                hasProperty("name", is("Harry Potter")),
                                hasProperty("genre", is("Fantasy")),
                                hasProperty("created", is(book.getCreated())))));
    }

    @Test
    public void saveBook() throws Exception {
        Book book = CreateBook.getTestBook();

        mockMvc.perform(post("/saveBook")
                .param("id", String.valueOf(book.getId()))
                .param("name", String.valueOf(book.getName()))
                .param("genre", String.valueOf(book.getGenre()))
                .param("created", String.valueOf(book.getCreated())))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}

