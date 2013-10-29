package com.epam.ticketsonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.ticketsonline.dao.TicketDao;
import com.epam.ticketsonline.entity.BookedInfo;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.service.TicketService;
import com.epam.ticketsonline.service.data.BookedTicketFilterData;
import com.epam.ticketsonline.service.data.TicketFilterData;

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
    public List<Ticket> getTickets(final TicketFilterData ticketFilterData) {
        return ticketDao.getTickets(ticketFilterData.getDate(),
                ticketFilterData.getTitle(),
                ticketFilterData.getTicketCategory());
    }

    @Override
    public List<BookedInfo> getBookedTickets(
            final BookedTicketFilterData bookedTicketFilterData) {
        return ticketDao.getBookedTickets(
                bookedTicketFilterData.getUserName(),
                bookedTicketFilterData.getDate(),
                bookedTicketFilterData.getTitle(),
                bookedTicketFilterData.getTicketCategory());
    }

    @Override
    public void bookTicket(final String ticketId, final String userName) {
        ticketDao.bookTicket(ticketId, userName);
    }

}
