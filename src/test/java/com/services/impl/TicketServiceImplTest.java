package com.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.utils.*;

@SpringBootTest
public class TicketServiceImplTest {

    @Mock
    private Map<String, Ticket> tickets;

    @Mock
    private Map<String, Set<User>> usersBySection;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    public void setUp() {
        tickets = new HashMap<>();
        usersBySection = new HashMap<>();
    }

    @Test
    public void testPurchaseTicket() {
        TicketRequest request = new TicketRequest();
        request.setFrom("New York");
        request.setTo("Los Angeles");
        User user = new User("John", "Doe", "john@example.com");
        request.setUser(user);

        Ticket ticket = ticketService.purchaseTicket(request);

        assertNotNull(ticket);
        assertEquals("New York", ticket.getFrom());
        assertEquals("Los Angeles", ticket.getTo());
        assertEquals(user, ticket.getUser());
        assertNotNull(ticket.getTicketId());
    }

    @Test
    public void testGetReceipt_UserFound() {
        String userEmail = "john@example.com";
        Ticket ticket = null;
        ticket.setUser(new User("Lisa", "Head", userEmail));
        tickets.put("1", ticket);

        Ticket receipt = ticketService.getReceipt(userEmail);

        assertNotNull(receipt);
        assertEquals(userEmail, receipt.getUser().getEmail());
    }

    @Test
    public void testGetReceipt_UserNotFound() {
        String userEmail = "jane@example.com";

        Ticket receipt = ticketService.getReceipt(userEmail);

        assertNull(receipt);
    }

    @Test
    public void testGetUsersBySection() {
        Map<String, Set<User>> expectedUsersBySection = new HashMap<>();
        Set<User> usersInSectionA = new HashSet<>();
        usersInSectionA.add(new User("John", "Doe", "john@example.com"));
        Set<User> usersInSectionB = new HashSet<>();
        usersInSectionB.add(new User("Jane", "Smith", "jane@example.com"));
        expectedUsersBySection.put("SectionA", usersInSectionA);
        expectedUsersBySection.put("SectionB", usersInSectionB);

        usersBySection.put("SectionA", usersInSectionA);
        usersBySection.put("SectionB", usersInSectionB);

        Map<String, Set<User>> usersBySectionResult = ticketService.getUsersBySection();

        assertEquals(expectedUsersBySection, usersBySectionResult);
    }

    @Test
    public void testRemoveUser_UserFound() {
        User user = new User("John", "Doe", "john@example.com");
        Set<User> usersInSectionA = new HashSet<>();
        usersInSectionA.add(user);
        usersBySection.put("SectionA", usersInSectionA);

        String result = ticketService.removeUser(user);

        assertEquals("User Deleted", result);
        assertFalse(usersInSectionA.contains(user));
    }

    @Test
    public void testRemoveUser_UserNotFound() {
        User user = new User("Jane", "Smith", "jane@example.com");

        String result = ticketService.removeUser(user);

        assertEquals("Not Available", result);
    }

    @Test
    public void testModifyUserSeat_TicketFound() {
        String ticketId = "1";
        String newSeat = "B3";
        Ticket ticket = new Ticket("New York", "Los Angeles", new User(), 20.0, "A1", ticketId);
        tickets.put(ticketId, ticket);
        ModifySeatData modifySeatData = new ModifySeatData(ticketId, newSeat);

        ticketService.modifyUserSeat(modifySeatData);

        assertEquals(newSeat, tickets.get(ticketId).getSeat());
    }

    @Test
    public void testModifyUserSeat_TicketNotFound() {
        String ticketId = "2";
        String newSeat = "B3";
        ModifySeatData modifySeatData = new ModifySeatData(ticketId, newSeat);

        ticketService.modifyUserSeat(modifySeatData);

        assertNull(tickets.get(ticketId));
    }
}
