package com.study.library.repository;

import com.study.library.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ayzat on 02.02.2016.
 */
@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addBook(Book book) {
            sessionFactory.getCurrentSession().saveOrUpdate(book);
        }

    public List<Book> listAll() {
        return sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    public void removeBook(Integer id) {
        Book book = (Book) sessionFactory.getCurrentSession().load(Book.class, id);
        if (null != book) {
            sessionFactory.getCurrentSession().delete(book);
        }
    }

    public Book getBook(Integer id) {
        return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
    }
}
