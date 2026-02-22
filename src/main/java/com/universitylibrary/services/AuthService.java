package com.universitylibrary.services;

import com.universitylibrary.models.*;
import com.universitylibrary.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private final List<Student> students = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Admin> admins = new ArrayList<>();

    public void registerStudent(String username, String password) {
        if (students.stream().anyMatch(s -> s.getUsername().equals(username))) {
            throw new UserAlreadyExistsException("Student already exists.");
        }
        students.add(new Student(username, password));
    }

    public Student loginStudent(String username, String password) {
        return students.stream()
                .filter(s -> s.getUsername().equals(username)
                        && s.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("Invalid student credentials."));
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public Admin loginAdmin(String username, String password) {
        return admins.stream()
                .filter(a -> a.getUsername().equals(username)
                        && a.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("Invalid admin credentials."));
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee loginEmployee(String username, String password) {
        return employees.stream()
                .filter(e -> e.getUsername().equals(username)
                        && e.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("Invalid employee credentials."));
    }

    public List<Student> getStudents() {
        return students;
    }
}