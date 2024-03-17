package com.utils;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    @Test
    public void testTicketConstructorAndGetters() {
        User user = new User("Rama","Srinivas" ,"sri@example.com");
        Ticket ticket = new Ticket("New York", "Los Angeles", user, 100.0, "A2","45K");

        assertEquals("New York", ticket.getFrom());
        assertEquals("Los Angeles", ticket.getTo());
        assertEquals(user, ticket.getUser());
        assertEquals(100.0, ticket.getPrice(), 0.001);
        assertEquals("B3", ticket.getSeat());
    }

    @Test
    public void testSetters() {
        User user = new User("Rama","Srinivas" ,"sri@example.com");
        Ticket ticket = new Ticket();

        ticket.setFrom("Chicago");
        ticket.setTo("San Francisco");
        ticket.setUser(user);
        ticket.setPrice(150.0);
        ticket.setSeat("A4");
        ticket.setTicketId("45F");

        assertEquals("Chicago", ticket.getFrom());
        assertEquals("San Francisco", ticket.getTo());
        assertEquals(user, ticket.getUser());
        assertEquals(150.0, ticket.getPrice(), 0.001);
        assertEquals("A4", ticket.getSeat());
        assertEquals("45F", ticket.getTicketId());
    }
}
