package com.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.services.impl.TicketServiceImpl;
import com.utils.*;

@SpringBootTest
public class TicketServiceTest {

    @Mock
    private TicketServiceImpl ticketService;

    @InjectMocks
    private TicketService ticketServiceInterface = new TicketService() {
        @Override
        public Ticket purchaseTicket(TicketRequest ticketRequest) {
            return ticketService.purchaseTicket(ticketRequest);
        }

        @Override
        public Ticket getReceipt(String userEmail) {
            return ticketService.getReceipt(userEmail);
        }

        @Override
        public Map<String, Set<User>> getUsersBySection() {
            return ticketService.getUsersBySection();
        }

        @Override
        public String removeUser(User user) {
            return ticketService.removeUser(user);
        }

        @Override
        public void modifyUserSeat(ModifySeatData modifySeatData) {
            ticketService.modifyUserSeat(modifySeatData);
        }
    };

    @Test
    public void testPurchaseTicket() {
        TicketRequest request = new TicketRequest();
        Ticket expectedTicket = new Ticket();
        when(ticketService.purchaseTicket(request)).thenReturn(expectedTicket);

        Ticket actualTicket = ticketServiceInterface.purchaseTicket(request);

        assertEquals(expectedTicket, actualTicket);
    }

    @Test
    public void testGetReceipt_UserFound() {
        String userEmail = "john@example.com";
        Ticket expectedTicket = new Ticket();
        when(ticketService.getReceipt(userEmail)).thenReturn(expectedTicket);

        Ticket receipt = ticketServiceInterface.getReceipt(userEmail);

        assertEquals(expectedTicket, receipt);
    }

    @Test
    public void testGetReceipt_UserNotFound() {
        String userEmail = "jane@example.com";
        when(ticketService.getReceipt(userEmail)).thenReturn(null);

        Ticket receipt = ticketServiceInterface.getReceipt(userEmail);

        assertNull(receipt);
    }

    @Test
    public void testGetUsersBySection() {
        Map<String, Set<User>> expectedUsersBySection = new HashMap<>();
        when(ticketService.getUsersBySection()).thenReturn(expectedUsersBySection);

        Map<String, Set<User>> usersBySectionResult = ticketServiceInterface.getUsersBySection();

        assertEquals(expectedUsersBySection, usersBySectionResult);
    }

    @Test
    public void testRemoveUser_UserFound() {
        User user = new User("John", "Doe", "john@example.com");
        String expectedResult = "User Deleted";
        when(ticketService.removeUser(user)).thenReturn(expectedResult);

        String result = ticketServiceInterface.removeUser(user);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testRemoveUser_UserNotFound() {
        User user = new User("Jane", "Smith", "jane@example.com");
        String expectedResult = "Not Available";
        when(ticketService.removeUser(user)).thenReturn(expectedResult);

        String result = ticketServiceInterface.removeUser(user);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testModifyUserSeat() {
        ModifySeatData modifySeatData = new ModifySeatData();
        ticketServiceInterface.modifyUserSeat(modifySeatData);
    }
}
