package com.universitylibrary.core;

import com.universitylibrary.services.*;

public class LibrarySystem {

    private final AuthService authService;
    private final BookService bookService;
    private final BorrowService borrowService;
    private final ReportService reportService;

    public LibrarySystem() {
        this.authService = new AuthService();
        this.bookService = new BookService();
        this.borrowService = new BorrowService();
        this.reportService = new ReportService();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public BorrowService getBorrowService() {
        return borrowService;
    }

    public ReportService getReportService() {
        return reportService;
    }
}