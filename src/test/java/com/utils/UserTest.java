package com.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        User user = new User("Ashwini", "Roy", "roy@example.com");

        assertEquals("Ashwini", user.getFirstName());
        assertEquals("Roy", user.getLastName());
        assertEquals("roy@example.com", user.getEmail());
    }

    @Test
    public void testSetters() {
        User user = new User("Ashwini", "Roy", "roy@example.com");

        user.setFirstName("Ashwini");
        user.setLastName("Roy");
        user.setEmail("roy@example.com");

        assertEquals("Ashwini", user.getFirstName());
        assertEquals("Roy", user.getLastName());
        assertEquals("roy@example.com", user.getEmail());
    }
}
