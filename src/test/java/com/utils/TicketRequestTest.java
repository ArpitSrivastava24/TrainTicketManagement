package com.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TicketRequestTest {

    @Test
    public void testGetterSetter() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setFrom("New York");
        assertEquals("New York", ticketRequest.getFrom());

        ticketRequest.setTo("Los Angeles");
        assertEquals("Los Angeles", ticketRequest.getTo());

        User user = new User("John", "Doe","john@example.com");
        ticketRequest.setUser(user);
        assertEquals(user, ticketRequest.getUser());
    }
}
