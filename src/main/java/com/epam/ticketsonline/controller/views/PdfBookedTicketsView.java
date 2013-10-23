package com.epam.ticketsonline.controller.views;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.epam.ticketsonline.entity.BookedInfo;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfBookedTicketsView extends AbstractPdfView {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    @Override
    protected void buildPdfDocument(final Map model, final Document document,
            final PdfWriter writer, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        final List<BookedInfo> ticketData = (List<BookedInfo>) model
                .get("tickets");

        final Table table = new Table(5);
        table.addCell("Title");
        table.addCell("Date");
        table.addCell("Category");
        table.addCell("Place");
        table.addCell("BOOKED BY");

        for (final BookedInfo bookedTicket : ticketData) {
            table.addCell(bookedTicket.getTicket().getTitle());
            table.addCell(dateFormatter.format(bookedTicket.getTicket()
                    .getDate()));
            table.addCell(bookedTicket.getTicket().getCategory().toString());
            table.addCell(Integer.toString(bookedTicket.getTicket().getPlace()));
            table.addCell(bookedTicket.getUserName());
        }

        document.add(table);
    }

}