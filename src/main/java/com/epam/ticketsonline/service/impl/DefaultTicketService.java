package com.epam.ticketsonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.ticketsonline.controller.model.BookedTicketFilterModel;
import com.epam.ticketsonline.controller.model.TicketFilterModel;
import com.epam.ticketsonline.dao.TicketDao;
import com.epam.ticketsonline.entity.BookedInfo;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.service.TicketService;

@Service("ticketService")
public class DefaultTicketService implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public List<String> getDatesOfMovieScreenings() {
        return ticketDao.getDatesOfMovieScreenings();
    }

    @Override
    public List<String> getMovies() {
        return ticketDao.getMovies();
    }

    @Override
    public List<String> getCategories() {
        return ticketDao.getCategories();
    }

    @Override
    public List<Ticket> getTickets(final TicketFilterModel ticketFilterModel) {
        return ticketDao.getTickets(ticketFilterModel.getDate(),
                ticketFilterModel.getTitle(),
                ticketFilterModel.getTicketCategory());
    }

    @Override
    public List<BookedInfo> getBookedTickets(
            final BookedTicketFilterModel bookedTicketFilterModel) {
        return ticketDao.getBookedTickets(
                bookedTicketFilterModel.getUserName(),
                bookedTicketFilterModel.getDate(),
                bookedTicketFilterModel.getTitle(),
                bookedTicketFilterModel.getTicketCategory());
    }

    @Override
    public void bookTicket(final String ticketId, final String userName) {
        ticketDao.bookTicket(ticketId, userName);
    }

}
