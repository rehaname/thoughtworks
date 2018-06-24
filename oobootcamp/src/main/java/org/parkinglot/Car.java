package org.parkinglot;

class Car {
    private Ticket assignedTicket;

    void setTicket(Ticket assignedTicket) {
        this.assignedTicket = assignedTicket;
    }

    boolean checkAssignedTicket(Ticket ticket) {
        return this.assignedTicket != null && this.assignedTicket.getTicketNumber() == ticket.getTicketNumber();
    }
}
