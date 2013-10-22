package com.epam.ticketsonline.controller.views;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.epam.ticketsonline.entity.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfTicketsView extends AbstractPdfView {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    @Override
    protected void buildPdfDocument(final Map model, final Document document,
            final PdfWriter writer, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        final List<Ticket> ticketData = (List<Ticket>) model.get("tickets");

        final Table table = new Table(4);
        table.addCell("Title");
        table.addCell("Date");
        table.addCell("Category");
        table.addCell("Place");

        for (final Ticket ticket : ticketData) {
            table.addCell(ticket.getTitle());
            table.addCell(dateFormatter.format(ticket.getDate()));
            table.addCell(ticket.getCategory().toString());
            table.addCell(Integer.toString(ticket.getPlace()));
        }

        document.add(table);
    }

}