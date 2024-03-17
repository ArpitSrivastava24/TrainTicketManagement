package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.TicketService;
import com.trainticketManager.TrainTicketManager.TrainTicketManagerApplication;
import com.utils.ModifySeatData;
import com.utils.Ticket;
import com.utils.TicketRequest;
import com.utils.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TrainTicketManagerApplication.class)
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    public void testPurchaseTicket() throws Exception {
        TicketRequest request = new TicketRequest();
        // set up request object

        Ticket ticket = new Ticket("London","France",null,20.00,"A1","A23D");

        when(ticketService.purchaseTicket(request)).thenReturn(ticket);

        mockMvc.perform(post("/api/tickets/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Add assertions as per your response structure
    }

    @Test
    public void testGetReceipt() throws Exception {
        TicketRequest request = new TicketRequest();
        // set up request object

        Ticket ticket = new Ticket("London","France",null,20.00,"A1","A23D"); // create Ticket object as per requirement

        when(ticketService.getReceipt(request.getUser().getEmail())).thenReturn(ticket);

        mockMvc.perform(get("/api/tickets/receipt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Add assertions as per your response structure
    }

    @Test
    public void testGetUsersBySection() throws Exception {
        Map<String, Set<User>> usersBySection = new HashMap<>();
        // set up usersBySection

        when(ticketService.getUsersBySection()).thenReturn(usersBySection);

        mockMvc.perform(get("/api/tickets/users-by-section")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Add assertions as per your response structure
    }

    @Test
    public void testRemoveUser() throws Exception {
        User user = new User("Alex","Adam","alex@mail.com"); // create User object as per requirement
        // set up user object

        when(ticketService.removeUser(user)).thenReturn("response");

        mockMvc.perform(delete("/api/tickets/remove-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("response"));
    }

    @Test
    public void testModifyUserSeat() throws Exception {
        ModifySeatData modifySeatData = new ModifySeatData(); // create ModifySeatData object as per requirement
        // set up modifySeatData object

        mockMvc.perform(put("/api/tickets/modify-seat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(modifySeatData)))
                .andExpect(status().isOk())
                .andExpect(content().string("Seat Modified"));
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
