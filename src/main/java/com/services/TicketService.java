package com.services;

import com.utils.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public interface TicketService {

    Ticket purchaseTicket(TicketRequest ticketRequest);
    Ticket getReceipt(String userEmail);
    Map<String, Set<User>> getUsersBySection();
    String removeUser(User user);
    void modifyUserSeat(ModifySeatData modifySeatData);
}
