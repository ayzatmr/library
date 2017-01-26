package com.study.library.util;

import com.study.library.domain.Book;

import java.util.Date;

/**
 * Created by Ayzat on 09.02.2016.
 */
public class CreateBook {

    private static final String CURRENT_DATE = new Date().toString();

    public static Book getTestBook()
    {
        Book book = new Book();
        book.setId(1);
        book.setName("Harry Potter");
        book.setGenre("Fantasy");
        book.setCreated(CURRENT_DATE);
        return book;
    }
}
