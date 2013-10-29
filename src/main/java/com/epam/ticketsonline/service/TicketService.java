package com.epam.ticketsonline.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.ticketsonline.entity.BookedInfo;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.service.data.BookedTicketFilterData;
import com.epam.ticketsonline.service.data.TicketFilterData;

@Transactional(propagation=Propagation.REQUIRED)
public interface TicketService {

    List<String> getDatesOfMovieScreenings();

    List<Ticket> getTickets(TicketFilterData ticketFilterData);

    List<BookedInfo> getBookedTickets(BookedTicketFilterData bookedTicketFilterData);

    List<String> getMovies();

    List<String> getCategories();

    void bookTicket(String ticketId, String userName);
}
