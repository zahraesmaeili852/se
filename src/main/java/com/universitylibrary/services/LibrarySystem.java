package main.java.com.universitylibrary.services;

import main.java.com.universitylibrary.models.*;
import java.time.LocalDate;
import java.util.*;

public class LibrarySystem {
    private List<Student> students = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<BorrowRequest> requests = new ArrayList<>();
    private Admin admin = new Admin("admin", "1234");

    // ========== Guest Menu ==========
    public void guestMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Guest Menu ---");
            System.out.println("1. View student count");
            System.out.println("2. Search book by title");
            System.out.println("3. View statistics");
            System.out.println("4. Back");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> System.out.println("Total registered students: " + students.size());
                case 2 -> {
                    System.out.print("Enter title: ");
                    String t = sc.nextLine().toLowerCase();
                    books.stream().filter(b -> b.getTitle().toLowerCase().contains(t)).forEach(System.out::println);
                }
                case 3 -> {
                    long borrowed = requests.stream().filter(BorrowRequest::isApproved).count();
                    System.out.println("Books: " + books.size());
                    System.out.println("Students: " + students.size());
                    System.out.println("Total borrows: " + borrowed);
                }
                case 4 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ========== Student Menu ==========
    public void studentMenu(Scanner sc) {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();
            students.add(new Student(u, p));
            System.out.println("Registered successfully!");
            return;
        }

        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        Student s = students.stream()
                .filter(st -> st.getUsername().equals(u) && st.getPassword().equals(p))
                .findFirst().orElse(null);

        if (s == null) {
            System.out.println("Invalid credentials.");
            return;
        }
        if (!s.isActive()) {
            System.out.println("Your account is deactivated.");
            return;
        }

        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Search book");
            System.out.println("2. Request borrow");
            System.out.println("3. View my borrow history");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1 -> {
                    System.out.print("Enter keyword (title/author/year): ");
                    String key = sc.nextLine().toLowerCase();
                    books.stream().filter(b ->
                            b.getTitle().toLowerCase().contains(key) ||
                                    b.getAuthor().toLowerCase().contains(key) ||
                                    String.valueOf(b.getYear()).contains(key)
                    ).forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Book b = books.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
                    if (b == null || !b.isAvailable()) {
                        System.out.println("Book not available.");
                        break;
                    }
                    System.out.print("Start date (YYYY-MM-DD): ");
                    LocalDate sDate = LocalDate.parse(sc.nextLine());
                    System.out.print("End date (YYYY-MM-DD): ");
                    LocalDate eDate = LocalDate.parse(sc.nextLine());
                    BorrowRequest req = new BorrowRequest(s, b, sDate, eDate);
                    requests.add(req);
                    s.getBorrowHistory().add(req);
                    System.out.println("Request submitted.");
                }
                case 3 -> s.getBorrowHistory().forEach(System.out::println);
                case 4 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ========== Employee Menu ==========
    public void employeeMenu(Scanner sc) {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        Employee e = employees.stream()
                .filter(emp -> emp.getUsername().equals(u) && emp.getPassword().equals(p))
                .findFirst().orElse(null);

        if (e == null) {
            System.out.println("Invalid credentials.");
            return;
        }

        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Change password");
            System.out.println("2. Add book");
            System.out.println("3. Edit book");
            System.out.println("4. Approve borrow requests");
            System.out.println("5. Student report");
            System.out.println("6. Activate/Deactivate student");
            System.out.println("7. Receive returned book");
            System.out.println("8. Logout");
            System.out.print("Choice: ");
            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1 -> {
                    System.out.print("New password: ");
                    e.setPassword(sc.nextLine());
                    System.out.println("Password changed.");
                }
                case 2 -> {
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    System.out.print("Year: ");
                    int y = sc.nextInt();
                    sc.nextLine();
                    books.add(new Book(t, a, y));
                    System.out.println("Book added.");
                }
                case 3 -> {
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Book b = books.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
                    if (b == null) { System.out.println("Not found."); break; }
                    System.out.print("New title: ");
                    b.setAvailable(true);
                    System.out.println("Book updated (for simplicity only availability reset).");
                }
                case 4 -> {
                    requests.stream()
                            .filter(r -> !r.isApproved() &&
                                    (r.getStartDate().equals(LocalDate.now()) || r.getStartDate().isBefore(LocalDate.now())))
                            .forEach(r -> {
                                r.setApproved(true);
                                r.getBook().setAvailable(false);
                                System.out.println("Approved: " + r);
                            });
                }
                case 5 -> {
                    System.out.print("Student username: ");
                    String name = sc.nextLine();
                    Student s = students.stream().filter(st -> st.getUsername().equals(name)).findFirst().orElse(null);
                    if (s == null) { System.out.println("Not found."); break; }
                    System.out.println("Borrow history:");
                    s.getBorrowHistory().forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Student username: ");
                    String name = sc.nextLine();
                    Student s = students.stream().filter(st -> st.getUsername().equals(name)).findFirst().orElse(null);
                    if (s == null) { System.out.println("Not found."); break; }
                    s.setActive(!s.isActive());
                    System.out.println("Student status changed: " + s);
                }
                case 7 -> {
                    System.out.print("Request ID by Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    BorrowRequest r = requests.stream().filter(req -> req.getBook().getId() == id && req.isApproved()).findFirst().orElse(null);
                    if (r == null) { System.out.println("Not found."); break; }
                    r.setReturned(true);
                    r.getBook().setAvailable(true);
                    System.out.println("Book returned successfully.");
                }
                case 8 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ========== Admin Menu ==========
    public void adminMenu(Scanner sc) {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        if (!u.equals(admin.getUsername()) || !p.equals(admin.getPassword())) {
            System.out.println("Invalid admin credentials.");
            return;
        }

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add employee");
            System.out.println("2. View employee performance");
            System.out.println("3. Borrow statistics");
            System.out.println("4. Student delay report");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            int c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1 -> {
                    System.out.print("Employee username: ");
                    String eu = sc.nextLine();
                    System.out.print("Password: ");
                    String ep = sc.nextLine();
                    employees.add(new Employee(eu, ep));
                    System.out.println("Employee added.");
                }
                case 2 -> System.out.println("Feature simplified: Only employee count shown: " + employees.size());
                case 3 -> {
                    long totalReq = requests.size();
                    long approved = requests.stream().filter(BorrowRequest::isApproved).count();
                    System.out.println("Total requests: " + totalReq);
                    System.out.println("Approved requests: " + approved);
                }
                case 4 -> System.out.println("Feature simplified: delay report not implemented.");
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
