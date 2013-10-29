package com.epam.ticketsonline.service.data;


public class BookedTicketFilterData extends TicketFilterData {
    private String userName;

    public BookedTicketFilterData() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

}
