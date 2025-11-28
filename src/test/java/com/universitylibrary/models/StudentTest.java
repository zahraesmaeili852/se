package com.universitylibrary.models;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void testStudentConstructorAndGetters() {
        Student student = new Student("emma", "pass123");

        assertEquals("emma", student.getUsername(), "Username should match");
        assertEquals("pass123", student.getPassword(), "Password should match");
        assertTrue(student.isActive(), "Student should be active by default");
        assertNotNull(student.getBorrowHistory(), "Borrow history list should be initialized");
        assertEquals(0, student.getBorrowHistory().size(), "Borrow history should be empty initially");
    }

    @Test
    void testSetActive() {
        Student student = new Student("emma", "pass123");

        student.setActive(false);
        assertFalse(student.isActive(), "Student should be inactive after setActive(false)");

        student.setActive(true);
        assertTrue(student.isActive(), "Student should be active after setActive(true)");
    }

    @Test
    void testBorrowHistory() {
        Student student = new Student("emma", "pass123");
        Book book = new Book("Algorithms", "Alice", 2022);
        BorrowRequest request = new BorrowRequest(student, book, java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(7));

        student.getBorrowHistory().add(request);
        List<BorrowRequest> history = student.getBorrowHistory();

        assertEquals(1, history.size(), "Borrow history should have 1 entry");
        assertEquals(request, history.get(0), "The entry in borrow history should match the added request");
    }

    @Test
    void testToString() {
        Student student = new Student("emma", "pass123");
        String expectedActive = "emma (Active)";
        assertEquals(expectedActive, student.toString(), "toString should show active status");

        student.setActive(false);
        String expectedInactive = "emma (Inactive)";
        assertEquals(expectedInactive, student.toString(), "toString should show inactive status");
    }
}

