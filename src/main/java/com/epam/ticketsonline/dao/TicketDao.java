package com.epam.ticketsonline.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.epam.ticketsonline.entity.BookedTicket;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.entity.TicketCategory;

public interface TicketDao {
    List<Ticket> getTickets(Date date, String title, TicketCategory category);

    List<BookedTicket> getBookedTickets(String userName, Date date, String title, TicketCategory category);

    Set<String> getDatesOfMovieScreenings();

    void setTicketBooked(String ticketId, String userName);

    Set<String> getMovies();

    Set<String> getCategories();
}
