package com.universitylibrary.services;

import com.universitylibrary.models.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReportService {

    public long countTotalBorrows(Student student) {
        return student.getBorrowHistory().stream()
                .filter(BorrowRequest::isApproved)
                .count();
    }

    public long countReturnedBooks(Student student) {
        return student.getBorrowHistory().stream()
                .filter(BorrowRequest::isReturned)
                .count();
    }

    public double calculateAverageBorrowDays(Student student) {

        return student.getBorrowHistory().stream()
                .filter(BorrowRequest::isApproved)
                .mapToLong(r -> ChronoUnit.DAYS.between(r.getStartDate(), r.getEndDate()))
                .average()
                .orElse(0.0);
    }
}