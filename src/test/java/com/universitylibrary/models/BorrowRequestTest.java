package com.universitylibrary.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowRequestTest {

    @Test
    void testBorrowRequestConstructorAndGetters() {
        Student student = new Student("sarah", "password123");
        Book book = new Book("Algorithms", "Alice", 2022);
        LocalDate start = LocalDate.of(2025, 11, 26);
        LocalDate end = LocalDate.of(2025, 12, 5);

        BorrowRequest request = new BorrowRequest(student, book, start, end);

        assertEquals(student, request.getStudent());
        assertEquals(book, request.getBook());
        assertEquals(start, request.getStartDate());
        assertEquals(end, request.getEndDate());
        assertFalse(request.isApproved(), "BorrowRequest should be pending by default");
        assertFalse(request.isReturned(), "BorrowRequest should not be returned by default");
    }

    @Test
    void testSetApprovedAndReturned() {
        Student student = new Student("sarah", "password123");
        Book book = new Book("Algorithms", "Alice", 2022);
        BorrowRequest request = new BorrowRequest(student, book, LocalDate.now(), LocalDate.now().plusDays(7));

        request.setApproved(true);
        assertTrue(request.isApproved(), "BorrowRequest should be approved");

        request.setReturned(true);
        assertTrue(request.isReturned(), "BorrowRequest should be marked as returned");
    }

    @Test
    void testToString() {
        Student student = new Student("sarah", "password123");
        Book book = new Book("Algorithms", "Alice", 2022);
        BorrowRequest request = new BorrowRequest(student, book, LocalDate.of(2025, 11, 26), LocalDate.of(2025, 12, 5));

        String expectedPending = "sarah -> Algorithms (2025-11-26 to 2025-12-05) [Pending]";
        assertEquals(expectedPending, request.toString(), "toString should show pending status");

        request.setApproved(true);
        String expectedApproved = "sarah -> Algorithms (2025-11-26 to 2025-12-05) [Approved]";
        assertEquals(expectedApproved, request.toString(), "toString should show approved status");

        request.setReturned(true);
        String expectedReturned = "sarah -> Algorithms (2025-11-26 to 2025-12-05) [Approved] [Returned]";
        assertEquals(expectedReturned, request.toString(), "toString should show returned status");
    }
}

