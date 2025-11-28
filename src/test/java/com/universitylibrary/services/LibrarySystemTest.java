package com.universitylibrary.services;

import com.universitylibrary.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibrarySystemTest {

    private LibrarySystem system;

    @BeforeEach
    void setUp() {
        system = new LibrarySystem();
    }

    // ---------- Test 1: Add Student ----------
    @Test
    void testAddStudent() {
        Student s = new Student("zahra", "123");
        system.getStudents().add(s);

        assertEquals(1, system.getStudents().size());
        assertEquals("zahra", system.getStudents().get(0).getUsername());
    }

    // ---------- Test 2: Add Book ----------
    @Test
    void testAddBook() {
        Book b = new Book("Java", "Author1", 2020);
        system.getBooks().add(b);

        assertEquals(1, system.getBooks().size());
        assertEquals("Java", system.getBooks().get(0).getTitle());
    }

    // ---------- Test 3: Borrow Request ----------
    @Test
    void testBorrowRequestCreation() {
        Student s = new Student("zahra", "123");
        system.getStudents().add(s);

        Book b = new Book("Java", "Author1", 2020);
        system.getBooks().add(b);

        BorrowRequest req = new BorrowRequest(
                s, b,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        system.getRequests().add(req);
        s.getBorrowHistory().add(req);

        assertEquals(1, system.getRequests().size());
        assertFalse(req.isApproved());
        assertEquals("zahra", req.getStudent().getUsername());
    }

    // ---------- Test 4: Approve Request ----------
    @Test
    void testApproveRequest() {
        Student s = new Student("zahra", "123");
        Book b = new Book("Java", "Author1", 2020);
        system.getStudents().add(s);
        system.getBooks().add(b);

        BorrowRequest req = new BorrowRequest(
                s, b,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(3)
        );

        system.getRequests().add(req);

        // simulate employee approval logic
        req.setApproved(true);
        b.setAvailable(false);

        assertTrue(req.isApproved());
        assertFalse(b.isAvailable());
    }

    // ---------- Test 5: Employee password change ----------
    @Test
    void testEmployeePasswordChange() {
        Employee emp = new Employee("emp1", "oldpass");
        system.getEmployees().add(emp);

        emp.setPassword("newpass");

        assertEquals("newpass", system.getEmployees().get(0).getPassword());
    }

    // ---------- Test 6: Return Book ----------
    @Test
    void testReturnBook() {
        Student s = new Student("zahra", "123");
        Book b = new Book("Java", "Author1", 2020);
        system.getStudents().add(s);
        system.getBooks().add(b);

        BorrowRequest req = new BorrowRequest(
                s, b, LocalDate.now().minusDays(2), LocalDate.now()
        );
        req.setApproved(true);
        system.getRequests().add(req);

        // returning logic
        req.setReturned(true);
        b.setAvailable(true);

        assertTrue(req.isReturned());
        assertTrue(b.isAvailable());
    }
}
