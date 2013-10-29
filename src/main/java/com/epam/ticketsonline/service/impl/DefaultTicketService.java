package com.epam.ticketsonline.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.ticketsonline.dao.TicketDao;
import com.epam.ticketsonline.entity.BookedTicket;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.service.TicketService;
import com.epam.ticketsonline.service.data.TicketFilterData;

@Component("ticketService")
public class DefaultTicketService implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Set<String> getDatesOfMovieScreenings() {
        return ticketDao.getDatesOfMovieScreenings();
    }

    @Override
    public Set<String> getMovies() {
        return ticketDao.getMovies();
    }

    @Override
    public Set<String> getCategories() {
        return ticketDao.getCategories();
    }

    @Override
    public List<Ticket> getTickets(final TicketFilterData ticketFilterData) {
        return ticketDao.getTickets(ticketFilterData.getDate(),
                ticketFilterData.getTitle(),
                ticketFilterData.getTicketCategory());
    }

    @Override
    public List<BookedTicket> getBookedTickets(
            final BookedTicketFilterData bookedTicketFilterData) {
        return ticketDao.getBookedTickets(
                bookedTicketFilterData.getUserName(),
                bookedTicketFilterData.getDate(),
                bookedTicketFilterData.getTitle(),
                bookedTicketFilterData.getTicketCategory());
    }

    @Override
    public void setTicketBooked(final String ticketId, final String userName) {
        ticketDao.setTicketBooked(ticketId, userName);
    }

}
