package com.utils;

import com.utils.ModifySeatData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModifySeatDataTest {

    @Test
    public void testConstructorWithArguments() {
        String ticketId = "123";
        String newSeat = "A4";
        ModifySeatData modifySeatData = new ModifySeatData(ticketId, newSeat);

        assertEquals(ticketId, modifySeatData.getTicketId());
        assertEquals(newSeat, modifySeatData.getNewSeat());
    }

    @Test
    public void testDefaultConstructor() {
        ModifySeatData modifySeatData = new ModifySeatData();

        assertNull(modifySeatData.getTicketId());
        assertNull(modifySeatData.getNewSeat());
    }

    @Test
    public void testSettersAndGetters() {
        ModifySeatData modifySeatData = new ModifySeatData();
        String ticketId = "456";
        String newSeat = "B5";

        modifySeatData.setTicketId(ticketId);
        modifySeatData.setNewSeat(newSeat);

        assertEquals(ticketId, modifySeatData.getTicketId());
        assertEquals(newSeat, modifySeatData.getNewSeat());
    }
}
