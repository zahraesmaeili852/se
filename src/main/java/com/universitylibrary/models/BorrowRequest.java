package com.universitylibrary.models;


import java.time.LocalDate;

public class BorrowRequest {
    private Student student;
    private Book book;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean approved = false;
    private boolean returned = false;

    public BorrowRequest(Student student, Book book, LocalDate startDate, LocalDate endDate) {
        this.student = student;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Student getStudent() { return student; }
    public Book getBook() { return book; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }

    @Override
    public String toString() {
        return student.getUsername() + " -> " + book.getTitle() +
                " (" + startDate + " to " + endDate + ") " +
                (approved ? "[Approved]" : "[Pending]") +
                (returned ? " [Returned]" : "");
    }
}
