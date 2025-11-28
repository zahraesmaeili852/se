package com.universitylibrary.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void testEmployeeConstructorAndGetters() {
        Employee emp = new Employee("john", "password123");

        assertEquals("john", emp.getUsername(), "Username should match");
        assertEquals("password123", emp.getPassword(), "Password should match");
    }

    @Test
    void testSetPassword() {
        Employee emp = new Employee("john", "password123");

        emp.setPassword("newpass456");
        assertEquals("newpass456", emp.getPassword(), "Password should be updated");
    }

    @Test
    void testToString() {
        Employee emp = new Employee("john", "password123");

        assertEquals("john", emp.toString(), "toString should return username");
    }
}
