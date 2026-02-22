package com.universitylibrary;

import com.universitylibrary.core.LibrarySystem;
import com.universitylibrary.models.*;
import com.universitylibrary.services.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LibrarySystem system = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        AuthService authService = system.getAuthService();
        BookService bookService = system.getBookService();
        BorrowService borrowService = system.getBorrowService();
        ReportService reportService = system.getReportService();

        System.out.println("===== University Library Management System =====");

        while (true) {
            System.out.println("\n1. Register Student");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1 -> {
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();

                        authService.registerStudent(username, password);
                        System.out.println("Student registered successfully.");
                    }

                    case 2 -> {
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Year: ");
                        int year = scanner.nextInt();
                        scanner.nextLine();

                        bookService.addBook(title, author, year);
                        System.out.println("Book added successfully.");
                    }

                    case 3 -> {
                        System.out.print("Student username: ");
                        String username = scanner.nextLine();

                        Student student = authService.getStudents()
                                .stream()
                                .filter(s -> s.getUsername().equals(username))
                                .findFirst()
                                .orElseThrow();

                        Book book = bookService.getAllBooks().get(0);

                        BorrowRequest request = borrowService.requestBorrow(
                                student,
                                book,
                                LocalDate.now(),
                                LocalDate.now().plusDays(7)
                        );

                        borrowService.approveRequest(request);

                        System.out.println("Borrow approved successfully.");
                    }

                    case 4 -> {
                        System.out.println("Goodbye!");
                        return;
                    }

                    default -> System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}