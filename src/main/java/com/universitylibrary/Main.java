package main.java.com.universitylibrary;


import main.java.com.universitylibrary.services.LibrarySystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== University Library Management System =====");
        while (true) {
            System.out.println("\nSelect User Type:");
            System.out.println("1. Guest");
            System.out.println("2. Student");
            System.out.println("3. Employee");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> librarySystem.guestMenu(scanner);
                case 2 -> librarySystem.studentMenu(scanner);
                case 3 -> librarySystem.employeeMenu(scanner);
                case 4 -> librarySystem.adminMenu(scanner);
                case 5 -> {
                    System.out.println("Exiting system... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
