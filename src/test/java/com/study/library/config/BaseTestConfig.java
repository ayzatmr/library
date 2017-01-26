package com.study.library.config;

import com.study.library.controller.BookController;
import com.study.library.domain.Book;
import com.study.library.repository.BookRepository;
import com.study.library.validation.BookValidator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Ayzat on 09.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ContextConfiguration(classes = MainConfig.class)
public class BaseTestConfig {
    protected MockMvc mockMvc;

    @Mock
    protected BookRepository bookRepository;

    @Mock
    protected BookValidator bookValidator;

    @InjectMocks
    protected BookController bookController;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController, wac).build();
        Mockito.when(bookValidator.supports(Book.class)).thenReturn(true);
    }
}
