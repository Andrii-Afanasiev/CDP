package com.epam.ticketsonline.entity;

public class BookedTicket {
    private Ticket ticket;
    private String userName;

    public BookedTicket(final String userName, final Ticket ticket) {
        this.ticket = ticket;
        this.userName = userName;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(final Ticket ticket) {
        this.ticket = ticket;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

}
