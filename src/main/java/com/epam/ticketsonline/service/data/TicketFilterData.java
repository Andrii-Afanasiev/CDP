package com.epam.ticketsonline.service.data;

import java.util.Date;

import com.epam.ticketsonline.entity.TicketCategory;

public class TicketFilterData {

    private String title;
    private Date date;
    private TicketCategory ticketCategory;

    public TicketFilterData() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(final TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

}
