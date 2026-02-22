package com.universitylibrary.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySystemTest {

    @Test
    void librarySystemShouldInitializeAllServices() {

        LibrarySystem system = new LibrarySystem();

        assertNotNull(system.getAuthService(),
                "AuthService should be initialized");

        assertNotNull(system.getBookService(),
                "BookService should be initialized");

        assertNotNull(system.getBorrowService(),
                "BorrowService should be initialized");

        assertNotNull(system.getReportService(),
                "ReportService should be initialized");
    }

    @Test
    void servicesShouldBeIndependentInstances() {

        LibrarySystem system = new LibrarySystem();

        assertNotSame(system.getAuthService(), system.getBookService());
        assertNotSame(system.getBorrowService(), system.getReportService());
    }
}