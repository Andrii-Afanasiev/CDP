package com.epam.ticketsonline.entity;

import java.util.Date;

public class Ticket {
    String id;
    String title;
    Date date;
    TicketCategory category;
    Integer place;

    public Ticket(final String id, final String title, final Date date,
            final TicketCategory category, final Integer place) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
        this.place = place;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Ticket)) {
            return false;
        }
        return id.equalsIgnoreCase(((Ticket) obj).getId());
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
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

    public TicketCategory getCategory() {
        return category;
    }

    public void setCategory(final TicketCategory category) {
        this.category = category;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(final Integer place) {
        this.place = place;
    }

}
