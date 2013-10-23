package com.epam.ticketsonline.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="TICKET")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID", unique = true, nullable = false)
    Integer id;
    @Column(name="TITLE")
    String title;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="DATE")
    Date date;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="CATEGORY")
    TicketCategory category;
    @Column(name="PLACE_NUMBER")
    Integer place;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ticket", cascade = CascadeType.ALL)
    private BookedInfo bookedInfo;

    public Ticket() {
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Ticket)) {
            return false;
        }
        return id==((Ticket) obj).getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
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

    public BookedInfo getBookedInfo() {
        return bookedInfo;
    }

    public void setBookedInfo(final BookedInfo bookedInfo) {
        this.bookedInfo = bookedInfo;
    }

}
