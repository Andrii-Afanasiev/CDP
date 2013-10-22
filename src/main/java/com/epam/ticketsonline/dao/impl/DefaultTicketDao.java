package com.epam.ticketsonline.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.epam.ticketsonline.dao.TicketDao;
import com.epam.ticketsonline.entity.BookedTicket;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.entity.TicketCategory;

@Component("ticketDao")
public class DefaultTicketDao implements TicketDao {
    private static List<Ticket> dataSourceTickets;
    private static Map<String, String> dataSourceBookedTickets;

    static {
        dataSourceTickets = new ArrayList<Ticket>();
        dataSourceBookedTickets = new HashMap<String, String>();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        try {
            dataSourceTickets.add(new Ticket("11", "Matrix", dateFormatter
                    .parse("2013-10-11 13:30"), TicketCategory.STANDARD, 1));
            dataSourceTickets.add(new Ticket("12", "Matrix", dateFormatter
                    .parse("2013-10-11 13:30"), TicketCategory.STANDARD, 2));
            dataSourceTickets.add(new Ticket("13", "Matrix", dateFormatter
                    .parse("2013-10-11 13:30"), TicketCategory.PREMIUM, 3));
            dataSourceTickets.add(new Ticket("14", "Matrix", dateFormatter
                    .parse("2013-10-11 13:30"), TicketCategory.PREMIUM, 4));
            dataSourceTickets.add(new Ticket("15", "Matrix", dateFormatter
                    .parse("2013-10-11 13:30"), TicketCategory.BAR, 5));
            dataSourceTickets.add(new Ticket("16", "Matrix", dateFormatter
                    .parse("2013-10-11 13:30"), TicketCategory.BAR, 6));
            // Next ticket group
            dataSourceTickets.add(new Ticket("21", "Terminator", dateFormatter
                    .parse("2013-10-11 15:00"), TicketCategory.STANDARD, 1));
            dataSourceTickets.add(new Ticket("22", "Terminator", dateFormatter
                    .parse("2013-10-11 15:00"), TicketCategory.STANDARD, 2));
            dataSourceTickets.add(new Ticket("23", "Terminator", dateFormatter
                    .parse("2013-10-11 15:00"), TicketCategory.PREMIUM, 3));
            dataSourceTickets.add(new Ticket("24", "Terminator", dateFormatter
                    .parse("2013-10-11 15:00"), TicketCategory.PREMIUM, 4));
            dataSourceTickets.add(new Ticket("25", "Terminator", dateFormatter
                    .parse("2013-10-11 15:00"), TicketCategory.BAR, 5));
            dataSourceTickets.add(new Ticket("26", "Terminator", dateFormatter
                    .parse("2013-10-11 15:00"), TicketCategory.BAR, 6));
            // Next ticket group
            dataSourceTickets.add(new Ticket("31", "Matrix", dateFormatter
                    .parse("2013-10-12 11:30"), TicketCategory.STANDARD, 1));
            dataSourceTickets.add(new Ticket("32", "Matrix", dateFormatter
                    .parse("2013-10-12 11:30"), TicketCategory.STANDARD, 2));
            dataSourceTickets.add(new Ticket("33", "Matrix", dateFormatter
                    .parse("2013-10-12 11:30"), TicketCategory.PREMIUM, 3));
            dataSourceTickets.add(new Ticket("34", "Matrix", dateFormatter
                    .parse("2013-10-12 14:20"), TicketCategory.PREMIUM, 4));
            dataSourceTickets.add(new Ticket("35", "Matrix", dateFormatter
                    .parse("2013-10-12 14:20"), TicketCategory.BAR, 5));
            dataSourceTickets.add(new Ticket("36", "Matrix", dateFormatter
                    .parse("2013-10-12 14:20"), TicketCategory.BAR, 6));
            // Next ticket group
            dataSourceTickets.add(new Ticket("41", "Star Wars", dateFormatter
                    .parse("2013-10-13 14:20"), TicketCategory.STANDARD, 1));
            dataSourceTickets.add(new Ticket("42", "Star Wars", dateFormatter
                    .parse("2013-10-13 14:20"), TicketCategory.STANDARD, 2));
            dataSourceTickets.add(new Ticket("43", "Star Wars", dateFormatter
                    .parse("2013-10-13 14:20"), TicketCategory.PREMIUM, 3));
            dataSourceTickets.add(new Ticket("44", "Star Wars", dateFormatter
                    .parse("2013-10-13 15:40"), TicketCategory.PREMIUM, 4));
            dataSourceTickets.add(new Ticket("45", "Star Wars", dateFormatter
                    .parse("2013-10-13 15:40"), TicketCategory.BAR, 5));
            dataSourceTickets.add(new Ticket("46", "Star Wars", dateFormatter
                    .parse("2013-10-13 15:40"), TicketCategory.BAR, 6));
        } catch (final ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getTickets(final Date date, final String title,
            final TicketCategory category) {
        final List<Ticket> result = new ArrayList<Ticket>();
        for (final Ticket ticket : dataSourceTickets) {
            addToResultIfRequired(result, ticket, date, title, category, false);
        }
        return result;
    }

    @Override
    public List<BookedTicket> getBookedTickets(final String userName,
            final Date date, final String title, final TicketCategory category) {
        final List<Ticket> ticketsFiltered = new ArrayList<Ticket>();
        final List<BookedTicket> ticketsBooked = new ArrayList<BookedTicket>();
        for (final Ticket ticket : dataSourceTickets) {
            addToResultIfRequired(ticketsFiltered, ticket, date, title,
                    category, true);
        }
        for (final Ticket ticket : ticketsFiltered) {
            if (dataSourceBookedTickets.containsKey(ticket.getId())) {
                if (userName != null && !userName.isEmpty()) {
                    if (dataSourceBookedTickets.get(ticket.getId())
                            .equalsIgnoreCase(userName)) {
                        ticketsBooked.add(new BookedTicket(userName, ticket));
                    }
                } else {
                    ticketsBooked.add(new BookedTicket(dataSourceBookedTickets
                            .get(ticket.getId()), ticket));
                }
            }
        }
        return ticketsBooked;
    }

    private void addToResultIfRequired(final List<Ticket> result,
            final Ticket ticket, final Date date, final String title,
            final TicketCategory category, final boolean getBooked) {
        boolean toBeAdded = true;
        if (date != null) {
            final Calendar dateOfticketWithoutHoursandMinutes = Calendar
                    .getInstance();
            dateOfticketWithoutHoursandMinutes.setTime(ticket.getDate());
            dateOfticketWithoutHoursandMinutes.set(Calendar.HOUR_OF_DAY, 0);
            dateOfticketWithoutHoursandMinutes.set(Calendar.MINUTE, 0);
            dateOfticketWithoutHoursandMinutes.set(Calendar.SECOND, 0);
            dateOfticketWithoutHoursandMinutes.set(Calendar.MILLISECOND, 0);
            if (!dateOfticketWithoutHoursandMinutes.getTime().equals(date)) {
                toBeAdded = false;
            }
        }
        if (title != null && !title.isEmpty()) {
            if (!ticket.getTitle().equalsIgnoreCase(title)) {
                toBeAdded = false;
            }
        }
        if (category != null) {
            if (!ticket.getCategory().equals(category)) {
                toBeAdded = false;
            }
        }
        if (!getBooked && dataSourceBookedTickets.containsKey(ticket.getId())) {
            toBeAdded = false;
        }
        if (toBeAdded) {
            result.add(ticket);
        }
    }

    @Override
    public Set<String> getDatesOfMovieScreenings() {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        final Set<String> dates = new HashSet<String>();
        for (final Ticket ticket : dataSourceTickets) {
            dates.add(dateFormatter.format(ticket.getDate()));
        }
        return dates;
    }

    @Override
    public Set<String> getMovies() {
        final Set<String> movies = new HashSet<String>();
        for (final Ticket ticket : dataSourceTickets) {
            movies.add(ticket.getTitle());
        }
        return movies;
    }

    @Override
    public Set<String> getCategories() {
        final Set<String> categories = new HashSet<String>();
        for (final TicketCategory category : TicketCategory.values()) {
            categories.add(category.name());
        }
        return categories;
    }

    @Override
    public void setTicketBooked(final String ticketId, final String userName) {
        for (final Ticket ticket : dataSourceTickets) {
            if (ticket.getId().equalsIgnoreCase(ticketId)) {
                dataSourceBookedTickets.put(ticketId, userName);
            }
        }
    }

}
