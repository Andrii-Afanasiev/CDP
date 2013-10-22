package com.epam.ticketsonline.controller.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.epam.ticketsonline.entity.TicketCategory;

public class TicketFilterModel {
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private TicketCategory ticketCategory;

    public TicketFilterModel() {
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
