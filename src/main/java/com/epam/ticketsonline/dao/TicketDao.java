package com.epam.ticketsonline.dao;

import java.util.Date;
import java.util.List;

import com.epam.ticketsonline.entity.BookedInfo;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.entity.TicketCategory;

public interface TicketDao {
    List<Ticket> getTickets(Date date, String title, TicketCategory category);

    List<BookedInfo> getBookedTickets(String userName, Date date, String title, TicketCategory category);

    List<String> getDatesOfMovieScreenings();

    void bookTicket(String ticketId, String userName);

    List<String> getMovies();

    List<String> getCategories();
}
