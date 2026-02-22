package com.universitylibrary.services;

import com.universitylibrary.models.*;
import com.universitylibrary.exceptions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowServiceTest {

    @Test
    void requestBorrowSuccessfully() {

        BorrowService borrowService = new BorrowService();
        Student student = new Student("ali", "123");
        Book book = new Book("Math", "Author", 2020);

        BorrowRequest request = borrowService.requestBorrow(
                student,
                book,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        assertFalse(request.isApproved());
        assertTrue(student.getBorrowHistory().contains(request));
    }

    @Test
    void inactiveStudentShouldThrowException() {

        BorrowService borrowService = new BorrowService();
        Student student = new Student("ali", "123");
        student.setActive(false);

        Book book = new Book("Math", "Author", 2020);

        assertThrows(InvalidStudentStatusException.class,
                () -> borrowService.requestBorrow(
                        student,
                        book,
                        LocalDate.now(),
                        LocalDate.now().plusDays(7)
                ));
    }

    @Test
    void approvingRequestShouldMakeBookUnavailable() {

        BorrowService borrowService = new BorrowService();
        Student student = new Student("ali", "123");
        Book book = new Book("Math", "Author", 2020);

        BorrowRequest request = borrowService.requestBorrow(
                student,
                book,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        borrowService.approveRequest(request);

        assertTrue(request.isApproved());
        assertFalse(book.isAvailable());
    }
}