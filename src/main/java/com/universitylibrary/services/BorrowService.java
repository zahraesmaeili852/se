package com.universitylibrary.services;

import com.universitylibrary.models.*;
import com.universitylibrary.exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowService {

    private final List<BorrowRequest> requests = new ArrayList<>();

    public BorrowRequest requestBorrow(Student student, Book book,
                                       LocalDate start, LocalDate end) {

        if (!student.isActive()) {
            throw new InvalidStudentStatusException("Student is inactive.");
        }

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is not available.");
        }

        BorrowRequest request = new BorrowRequest(student, book, start, end);
        requests.add(request);
        student.getBorrowHistory().add(request);

        return request;
    }

    public void approveRequest(BorrowRequest request) {
        if (request.isApproved()) {
            throw new InvalidRequestStatusException("Request already approved.");
        }

        request.setApproved(true);
        request.getBook().setAvailable(false);
    }

    public void returnBook(BorrowRequest request) {
        if (!request.isApproved()) {
            throw new InvalidRequestStatusException("Cannot return unapproved request.");
        }

        request.setReturned(true);
        request.getBook().setAvailable(true);
    }

    public List<BorrowRequest> getAllRequests() {
        return requests;
    }
}