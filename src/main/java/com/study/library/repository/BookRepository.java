package com.study.library.repository;

import com.study.library.domain.Book;

import java.util.List;

/**
 * Created by Ayzat on 08.02.2016.
 */
public interface BookRepository {
    void addBook(Book book);

    List<Book> listAll();

    void removeBook(Integer id);

    Book getBook(Integer id);
}
