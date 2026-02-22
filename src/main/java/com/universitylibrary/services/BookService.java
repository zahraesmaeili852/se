package com.universitylibrary.services;

import com.universitylibrary.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private final List<Book> books = new ArrayList<>();

    public void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }

    public List<Book> searchByTitle(String keyword) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return books;
    }
}