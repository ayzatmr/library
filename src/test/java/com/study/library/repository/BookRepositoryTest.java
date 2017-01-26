package com.study.library.repository;

import com.study.library.config.BaseTestConfig;
import com.study.library.domain.Book;
import com.study.library.util.CreateBook;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ayzat on 09.02.2016.
 */

//integration test

public class BookRepositoryTest extends BaseTestConfig{

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void addBook()
    {
        Book book = CreateBook.getTestBook();
        bookRepository.addBook(book);
        Book addedBook = bookRepository.getBook(book.getId());
        Assert.assertEquals(book.getName(), addedBook.getName());
        Assert.assertEquals(book.getGenre(), addedBook.getGenre());
        Assert.assertEquals(book.getId(), addedBook.getId());
    }
}

