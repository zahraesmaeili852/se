package com.universitylibrary.models;


public class Book {
    private static int counter = 1;
    private int id;
    private String title;
    private String author;
    private int year;
    private boolean available = true;

    public Book(String title, String author, int year) {
        this.id = counter++;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + " (" + year + ") - " + (available ? "Available" : "Borrowed");
    }
}
