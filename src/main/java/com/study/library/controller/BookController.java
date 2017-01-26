package com.study.library.controller;

import com.study.library.domain.Book;
import com.study.library.repository.BookRepository;
import com.study.library.validation.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Ayzat on 02.02.2016.
 */
@Controller
public class BookController {

    private BookValidator bookValidator;
    private BookRepository bookRepository;

    @Autowired
    BookController(BookValidator bookValidator, BookRepository bookRepository) {
        this.bookValidator = bookValidator;
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getBooks(ModelAndView model) {
        List books = this.bookRepository.listAll();
        model.addObject("books", books);
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "addBook", method = RequestMethod.GET)
    public ModelAndView addBook(ModelAndView model) {
        model.addObject("book", new Book());
        model.setViewName("addBook");
        return model;
    }

    @RequestMapping(value = {"saveBook", "editBook/saveBook"}, method = RequestMethod.POST)
    public ModelAndView saveBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {

        this.bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("addBook");
        }
        this.bookRepository.addBook(book);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "deleteBook/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBook(@PathVariable Integer id) {
        this.bookRepository.removeBook(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "editBook/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id) {
        Book book = bookRepository.getBook(id);
        ModelAndView model = new ModelAndView("addBook");
        model.addObject("book", book);
        return model;
    }
}
