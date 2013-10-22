package com.epam.ticketsonline.service;

import java.util.List;
import java.util.Set;

import com.epam.ticketsonline.controller.model.BookedTicketFilterModel;
import com.epam.ticketsonline.controller.model.TicketFilterModel;
import com.epam.ticketsonline.entity.BookedTicket;
import com.epam.ticketsonline.entity.Ticket;

public interface TicketService {

    Set<String> getDatesOfMovieScreenings();

    List<Ticket> getTickets(TicketFilterModel ticketFilterModel);

    List<BookedTicket> getBookedTickets(BookedTicketFilterModel bookedTicketFilterModel);

    Set<String> getMovies();

    Set<String> getCategories();

    void setTicketBooked(String ticketId, String userName);
}
