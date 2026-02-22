package com.universitylibrary.core;

import com.universitylibrary.models.*;
import com.universitylibrary.services.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySystemIntegrationTest {

    @Test
    void fullBorrowFlowShouldWorkCorrectly() {

        // 1️⃣ Create system
        LibrarySystem system = new LibrarySystem();

        AuthService auth = system.getAuthService();
        BookService bookService = system.getBookService();
        BorrowService borrowService = system.getBorrowService();
        ReportService reportService = system.getReportService();

        // 2️⃣ Register and login student
        auth.registerStudent("zahra", "1234");
        Student student = auth.loginStudent("zahra", "1234");

        assertNotNull(student);
        assertTrue(student.isActive());

        // 3️⃣ Add book
        bookService.addBook("Clean Code", "Robert Martin", 2008);
        Book book = bookService.getAllBooks().get(0);

        assertTrue(book.isAvailable());

        // 4️⃣ Request borrow
        BorrowRequest request = borrowService.requestBorrow(
                student,
                book,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        assertFalse(request.isApproved());
        assertEquals(1, student.getBorrowHistory().size());

        // 5️⃣ Approve request
        borrowService.approveRequest(request);

        assertTrue(request.isApproved());
        assertFalse(book.isAvailable());

        // 6️⃣ Return book
        borrowService.returnBook(request);

        assertTrue(request.isReturned());
        assertTrue(book.isAvailable());

        // 7️⃣ Generate report
        long totalBorrows = reportService.countTotalBorrows(student);
        long returnedBooks = reportService.countReturnedBooks(student);
        double avgDays = reportService.calculateAverageBorrowDays(student);

        assertEquals(1, totalBorrows);
        assertEquals(1, returnedBooks);
        assertEquals(7.0, avgDays);
    }
}