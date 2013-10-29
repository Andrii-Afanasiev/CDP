package com.epam.ticketsonline.service;

import java.util.List;
import java.util.Set;

import com.epam.ticketsonline.entity.BookedTicket;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.service.data.BookedTicketFilterData;
import com.epam.ticketsonline.service.data.TicketFilterData;

public interface TicketService {

    Set<String> getDatesOfMovieScreenings();

    List<Ticket> getTickets(TicketFilterData ticketFilterData);

    List<BookedTicket> getBookedTickets(BookedTicketFilterData bookedTicketFilterData);

    Set<String> getMovies();

    Set<String> getCategories();

    void setTicketBooked(String ticketId, String userName);
}
