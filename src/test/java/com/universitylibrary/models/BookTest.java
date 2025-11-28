package com.universitylibrary.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    void testBookConstructorAndGetters() {
        Book b = new Book("Mathematics", "Alice", 2023);

        assertEquals("Mathematics", b.getTitle(), "Title should match");
        assertEquals("Alice", b.getAuthor(), "Author should match");
        assertEquals(2023, b.getYear(), "Year should match");
        assertTrue(b.isAvailable(), "Book should be available by default");
        assertTrue(b.getId() > 0, "ID should be greater than 0");
    }

    @Test
    void testSetAvailable() {
        Book b = new Book("Physics", "Bob", 2022);

        assertTrue(b.isAvailable(), "Initially book should be available");

        b.setAvailable(false);
        assertFalse(b.isAvailable(), "Book should not be available after setAvailable(false)");

        b.setAvailable(true);
        assertTrue(b.isAvailable(), "Book should be available again after setAvailable(true)");
    }

    @Test
    void testToString() {
        Book b = new Book("Chemistry", "Carol", 2021);
        b.setAvailable(true);
        String expectedAvailable = "[" + b.getId() + "] Chemistry by Carol (2021) - Available";
        assertEquals(expectedAvailable, b.toString(), "toString should show Available status");

        b.setAvailable(false);
        String expectedBorrowed = "[" + b.getId() + "] Chemistry by Carol (2021) - Borrowed";
        assertEquals(expectedBorrowed, b.toString(), "toString should show Borrowed status");
    }
}




