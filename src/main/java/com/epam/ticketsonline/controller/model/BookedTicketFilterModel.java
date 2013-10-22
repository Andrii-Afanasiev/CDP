package com.epam.ticketsonline.controller.model;

public class BookedTicketFilterModel extends TicketFilterModel {
    private String userName;

    public BookedTicketFilterModel(){

    }

    public BookedTicketFilterModel(final String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

}
