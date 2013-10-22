package com.epam.ticketsonline.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.ticketsonline.controller.model.BookedTicketFilterModel;
import com.epam.ticketsonline.controller.model.TicketFilterModel;
import com.epam.ticketsonline.dao.TicketDao;
import com.epam.ticketsonline.entity.BookedTicket;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.service.TicketService;

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
    public List<Ticket> getTickets(final TicketFilterModel ticketFilterModel) {
        return ticketDao.getTickets(ticketFilterModel.getDate(),
                ticketFilterModel.getTitle(),
                ticketFilterModel.getTicketCategory());
    }

    @Override
    public List<BookedTicket> getBookedTickets(
            final BookedTicketFilterModel bookedTicketFilterModel) {
        return ticketDao.getBookedTickets(
                bookedTicketFilterModel.getUserName(),
                bookedTicketFilterModel.getDate(),
                bookedTicketFilterModel.getTitle(),
                bookedTicketFilterModel.getTicketCategory());
    }

    @Override
    public void setTicketBooked(final String ticketId, final String userName) {
        ticketDao.setTicketBooked(ticketId, userName);
    }

}
