package com.utils;

public class ModifySeatData {

    private String ticketId;

    private String newSeat;

    public ModifySeatData(String ticketId, String newSeat) {
        this.newSeat=newSeat;
        this.ticketId=ticketId;
    }

    public ModifySeatData() {
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getNewSeat() {
        return newSeat;
    }

    public void setNewSeat(String newSeat) {
        this.newSeat = newSeat;
    }


}
