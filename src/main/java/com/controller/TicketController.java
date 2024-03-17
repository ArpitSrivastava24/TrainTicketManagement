package com.controller;

import com.services.TicketService;
import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.purchaseTicket(ticketRequest));
    }

    @GetMapping("/receipt")
    public ResponseEntity<Ticket> getReceipt(@RequestBody TicketRequest ticketRequest) {
        Ticket ticket = ticketService.getReceipt(ticketRequest.getUser().getEmail());
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users-by-section")
    public ResponseEntity<Map<String, Set<User>>> getUsersBySection() {
        Map<String, Set<User>> usersBySection = ticketService.getUsersBySection();
        return ResponseEntity.ok(usersBySection);
    }

    @DeleteMapping("/remove-user")
    public ResponseEntity<String> removeUser(@RequestBody User user) {
        return ResponseEntity.ok( ticketService.removeUser(user));
    }

    @PutMapping("/modify-seat")
    public ResponseEntity<String> modifyUserSeat(@RequestBody ModifySeatData modifySeatData) {
        ticketService.modifyUserSeat(modifySeatData);
        return ResponseEntity.ok("Seat Modified");

    }
}
