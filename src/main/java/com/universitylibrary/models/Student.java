package com.universitylibrary.models;


import java.util.ArrayList;
import java.util.List;

public class Student {
    private String username;
    private String password;
    private boolean active = true;
    private List<BorrowRequest> borrowHistory = new ArrayList<>();

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public List<BorrowRequest> getBorrowHistory() { return borrowHistory; }

    @Override
    public String toString() {
        return username + " (" + (active ? "Active" : "Inactive") + ")";
    }
}
