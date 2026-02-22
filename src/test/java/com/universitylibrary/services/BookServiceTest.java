package com.universitylibrary.services;

import com.universitylibrary.models.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    @Test
    void addBookShouldIncreaseBookListSize() {

        BookService bookService = new BookService();

        bookService.addBook("Clean Code", "Robert Martin", 2008);

        assertEquals(1, bookService.getAllBooks().size());
    }

    @Test
    void searchByTitleShouldReturnMatchingBooks() {

        BookService bookService = new BookService();

        bookService.addBook("Clean Code", "Robert Martin", 2008);
        bookService.addBook("Effective Java", "Joshua Bloch", 2018);

        List<Book> result = bookService.searchByTitle("clean");

        assertEquals(1, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());
    }

    @Test
    void searchShouldBeCaseInsensitive() {

        BookService bookService = new BookService();

        bookService.addBook("Algorithms", "CLRS", 2020);

        List<Book> result = bookService.searchByTitle("ALGO");

        assertEquals(1, result.size());
    }
}