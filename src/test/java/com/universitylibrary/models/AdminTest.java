package com.universitylibrary.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    @Test
    void testAdminConstructorAndGetters() {
        Admin admin = new Admin("adminUser", "adminPass");

        assertEquals("adminUser", admin.getUsername(), "Username should match");
        assertEquals("adminPass", admin.getPassword(), "Password should match");
    }
}

