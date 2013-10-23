package com.epam.ticketsonline.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.ticketsonline.controller.model.BookedTicketFilterModel;
import com.epam.ticketsonline.controller.model.TicketFilterModel;
import com.epam.ticketsonline.entity.BookedInfo;
import com.epam.ticketsonline.entity.Ticket;

@Transactional(propagation=Propagation.REQUIRED)
public interface TicketService {

    List<String> getDatesOfMovieScreenings();

    List<Ticket> getTickets(TicketFilterModel ticketFilterModel);

    List<BookedInfo> getBookedTickets(BookedTicketFilterModel bookedTicketFilterModel);

    List<String> getMovies();

    List<String> getCategories();

    void bookTicket(String ticketId, String userName);
}
