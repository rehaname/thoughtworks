package org.parkinglot;

public class Car {
    Ticket assignedTicket;

    public void setTicket(Ticket assignedTicket) {
        this.assignedTicket = assignedTicket;
    }

    public boolean checkAssignedTicket(Ticket ticket) {
        return this.assignedTicket == null ? false : this.assignedTicket.getTicketNumber() == ticket.getTicketNumber();
    }
}
