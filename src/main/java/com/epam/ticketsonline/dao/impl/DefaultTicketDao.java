package com.epam.ticketsonline.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.ticketsonline.dao.TicketDao;
import com.epam.ticketsonline.entity.BookedInfo;
import com.epam.ticketsonline.entity.Ticket;
import com.epam.ticketsonline.entity.TicketCategory;

@Component("ticketDao")
public class DefaultTicketDao implements TicketDao {
    private static final String HQL_ALL_DATES = "select ticket.date from com.epam.ticketsonline.entity.Ticket as ticket";
    private static final String HQL_ALL_MOVIES = "select ticket.title from com.epam.ticketsonline.entity.Ticket as ticket";
    private static final String HQL_ALL_CATEGORIES = "select ticket.category from com.epam.ticketsonline.entity.Ticket as ticket";
    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Ticket> getTickets(final Date date, final String title,
            final TicketCategory category) {
        final Session session = sessionFactory.getCurrentSession();
        final Criteria criteria = session
                .createCriteria(Ticket.class, "ticket");
        if (date != null) {
            criteria.add(Expression.eq("ticket.date", date));
        }
        if (title != null && !title.isEmpty()) {
            criteria.add(Expression.eq("ticket.title", title));
        }
        if (category != null) {
            criteria.add(Expression.eq("ticket.category", category));
        }
        final List<Ticket> result = criteria.list();
        return result;
    }

    @Override
    public List<BookedInfo> getBookedTickets(final String userName,
            final Date date, final String title, final TicketCategory category) {
        final Session session = sessionFactory.getCurrentSession();
        final Criteria criteria = session.createCriteria(BookedInfo.class,
                "booked");
        if (userName != null && !userName.isEmpty()) {
            criteria.add(Expression.eq("booked.userName", userName));
        }
        if (date != null) {
            criteria.add(Expression.eq("booked.ticket.date", date));
        }
        if (title != null && !title.isEmpty()) {
            criteria.add(Expression.eq("booked.ticket.title", title));
        }
        if (category != null) {
            criteria.add(Expression.eq("booke.ticket.category", category));
        }
        final List<BookedInfo> result = criteria.list();
        for (final Object obj : result) {
            Hibernate.initialize(((BookedInfo)obj).getTicket());
        }
        return result;
    }

    @Override
    public List<String> getDatesOfMovieScreenings() {
        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery(HQL_ALL_DATES);
        return query.list();
    }

    @Override
    public List<String> getMovies() {
        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery(HQL_ALL_MOVIES);
        return query.list();
    }

    @Override
    public List<String> getCategories() {
        final Session session = sessionFactory.getCurrentSession();
        final Query query = session.createQuery(HQL_ALL_CATEGORIES);
        return query.list();
    }

    @Override
    public void bookTicket(final String ticketId, final String userName) {
        final Session session = sessionFactory.getCurrentSession();
        final Criteria criteria = session
                .createCriteria(Ticket.class, "ticket");
        criteria.add(Expression.eq("ticket.id", Integer.valueOf(ticketId)));
        final List res = criteria.list();
        final Ticket ticket = (Ticket) res.get(0);
        final BookedInfo bookedInfo = new BookedInfo();
        bookedInfo.setUserName(userName);
        bookedInfo.setTicket(ticket);
        ticket.setBookedInfo(bookedInfo);
        session.save(ticket);
    }

}
