package com.epam.ticketsonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.ticketsonline.controller.model.BookedTicketFilterModel;
import com.epam.ticketsonline.controller.model.TicketFilterModel;
import com.epam.ticketsonline.service.TicketService;
import com.epam.ticketsonline.service.data.BookedTicketFilterData;
import com.epam.ticketsonline.service.data.TicketFilterData;

@Controller("/action")
public class BookingController {

    private static final String BOOKING_FORM_VIEW_NAME = "ticketsForm";
    private static final String TICKETS_LIST_VIEW_NAME = "tickets";
    private static final String BOOKED_TICKETS_FORM_VIEW_NAME = "bookedTicketsForm";
    private static final String BOOKED_TICKETS_LIST_VIEW_NAME = "bookedTickets";

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/ticketsForm", method = RequestMethod.GET)
    public ModelAndView getTicketsForm() {
        final ModelAndView modelAndView = new ModelAndView(
                BOOKING_FORM_VIEW_NAME, "ticketFilter", new TicketFilterModel())
                .addObject("dates", ticketService.getDatesOfMovieScreenings())
                .addObject("movies", ticketService.getMovies())
                .addObject("categories", ticketService.getCategories());
        return modelAndView;
    }

    @RequestMapping(value = "/bookedTicketsForm", method = RequestMethod.GET)
    public ModelAndView getBookedticketsrForm() {
        final ModelAndView modelAndView = new ModelAndView(
                BOOKED_TICKETS_FORM_VIEW_NAME, "bookedTicketFilter",
                new BookedTicketFilterModel())
                .addObject("dates", ticketService.getDatesOfMovieScreenings())
                .addObject("movies", ticketService.getMovies())
                .addObject("categories", ticketService.getCategories());
        return modelAndView;
    }

    @RequestMapping(value = "/getBookedTickets", method = {
            RequestMethod.POST, RequestMethod.GET })
    public ModelAndView getBookedUsers(
            @ModelAttribute("bookedUserFilter") final BookedTicketFilterModel bookedTicketFilterModel) {
        final ModelAndView modelAndView = new ModelAndView(
                BOOKED_TICKETS_LIST_VIEW_NAME, "tickets",
                ticketService.getBookedTickets(convertBookedTicketFilterModelToData(bookedTicketFilterModel)));
        return modelAndView;
    }

    @RequestMapping(value = "/getTickets", method = { RequestMethod.POST,
            RequestMethod.GET }, produces={"text/html","application/pdf"})
    public ModelAndView getTickets(
            @ModelAttribute("ticketFilter") final TicketFilterModel ticketFilterModel) {
        final ModelAndView modelAndView = new ModelAndView(
                TICKETS_LIST_VIEW_NAME, "tickets",
                ticketService.getTickets(convertTicketFilterModelToData(ticketFilterModel)));
        System.out.println();
        return modelAndView;
    }

    @RequestMapping(value = "/bookTicket", method = RequestMethod.POST)
    public ModelAndView bookTicket(@RequestParam("id") final String ticketId,
            @RequestParam("name") final String userName) {
        ticketService.setTicketBooked(ticketId, userName);
        final ModelAndView modelAndView = new ModelAndView(
                BOOKED_TICKETS_LIST_VIEW_NAME).addObject("tickets",
                ticketService.getBookedTickets(convertBookedTicketFilterModelToData(new BookedTicketFilterModel(
                        userName))));
        return modelAndView;
    }

    private TicketFilterData convertTicketFilterModelToData(final TicketFilterModel model){
        final TicketFilterData data = new TicketFilterData();
        data.setDate(model.getDate());
        data.setTitle(model.getTitle());
        data.setTicketCategory(model.getTicketCategory());
        return data;
    }

    private BookedTicketFilterData convertBookedTicketFilterModelToData(final BookedTicketFilterModel model){
        final BookedTicketFilterData data = new BookedTicketFilterData();
        data.setUserName(model.getUserName());
        data.setTitle(model.getTitle());
        data.setDate(model.getDate());
        data.setTicketCategory(model.getTicketCategory());
        return data;
    }
}
