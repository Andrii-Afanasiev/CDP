package com.epam.ticketsonline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "BOOKED_INFO")
public class BookedInfo {
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "ticket"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "TICKET_ID", unique = true, nullable = false)
    private Integer ticketId;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Ticket ticket;
    @Column(name = "USER_NAME")
    private String userName;

    public BookedInfo() {
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(final Integer ticketId){
        this.ticketId = ticketId;
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
