package com.services.impl;

import com.services.TicketService;
import com.utils.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.utils.TrainSection.*;

@Service
public class TicketServiceImpl implements TicketService {
    private Map<String, Ticket> tickets = new HashMap<>();
    private Map<String, Set<User>> usersBySection = new HashMap<>();

    private HashSet<User> setA = new HashSet<>();
    private HashSet<User> setB = new HashSet<>();

    String section = null;

    @Override
    public Ticket purchaseTicket(TicketRequest ticketRequest) {
        // Generate a unique identifier for the ticket
        String ticketId = generateTicketId();

        // Allocate a seat to the user
        String seat = allocateSeat();

        // Create a new ticket
        Ticket ticket = new Ticket(ticketRequest.getFrom(), ticketRequest.getTo(), ticketRequest.getUser(),
                20.0, seat, ticketId);

        // Store the ticket
        storeTicket(ticketId, ticket);

        return ticket;
    }

    @Override
    public Ticket getReceipt(String userEmail) {
        for (Ticket ticket : tickets.values()) {
            if (ticket.getUser().getEmail().equals(userEmail)) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public Map<String, Set<User>> getUsersBySection() {
        return usersBySection;
    }

    @Override
    public String removeUser(User user) {
        boolean userFound = false;
        for (User i : setA) {
            if (user.getEmail().equals(i.getEmail())) {
                userFound = true;
                setA.remove(i);
            }
        }
        for (User i : setB) {
            if (user.getEmail().equals(i.getEmail())) {
                userFound = true;
                setB.remove(i);
            }
        }
        return userFound ? "User Deleted" : "Not Available";
    }

    @Override
    public void modifyUserSeat(ModifySeatData modifySeatData) {
        for (String i : tickets.keySet()) {
            if (modifySeatData.getTicketId().equals(i)) {
                Ticket temp = tickets.get(i);
                temp.setSeat(modifySeatData.getNewSeat());
                tickets.put(i, temp);
                break;
            }
        }
    }

    protected String generateTicketId() {
        // Implement logic to generate a unique ticket identifier (e.g., using UUID)
        return UUID.randomUUID().toString();
    }

    protected String allocateSeat() {
        if (!secA.isEmpty()) {
            section = "A";
            return secA.remove(0);
        } else if (!secB.isEmpty()) {
            section = "B";
            return secB.remove(0);
        }
        return "NO VACANT SEATS AVAILABLE";
    }

    protected void storeTicket(String ticketId, Ticket ticket) {
        tickets.put(ticketId, ticket);

        if (section.equals("A")) {
            setA.add(ticket.getUser());
        } else if (section.equals("B")) {
            setB.add(ticket.getUser());
        }

        usersBySection.put("sectionA", setA);
        usersBySection.put("sectionB", setB);
    }
}
