package com.universitylibrary.services;

import com.universitylibrary.models.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {

    @Test
    void calculateAverageBorrowDaysCorrectly() {

        Student student = new Student("zahra", "123");
        Book book = new Book("Algorithms", "CLRS", 2022);

        BorrowRequest request = new BorrowRequest(
                student,
                book,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 11)
        );

        request.setApproved(true);
        student.getBorrowHistory().add(request);

        ReportService reportService = new ReportService();

        double avg = reportService.calculateAverageBorrowDays(student);

        assertEquals(10.0, avg);
    }
}