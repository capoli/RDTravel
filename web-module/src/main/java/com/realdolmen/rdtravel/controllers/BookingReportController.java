package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Booking;
import com.realdolmen.rdtravel.domain.Report;
import com.realdolmen.rdtravel.persistence.BookingEJB;
import com.realdolmen.rdtravel.persistence.TripEJB;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BookingReportController implements Serializable {
    @EJB
    private BookingEJB bookingEJB;

    @EJB
    private TripEJB tripEJB;

    private Date periodStart;
    private Date periodEnd;
    private Report report;
    private List<Booking> bookings = new ArrayList<>();
    private List<Booking> filteredBookings;

    public void onPeriodChange(SelectEvent event) {
        processPeriod();
    }

    public void onFilterChange(AjaxBehaviorEvent event) {
        Double avg = null, min = null, max = null;
        if (filteredBookings != null) {
            for (Booking booking : filteredBookings) {
                Double totalPrice = booking.getTotalPrice();
                if (min == null && max == null && avg == null) {
                    min = totalPrice;
                    max = totalPrice;
                    avg = totalPrice;
                } else {
                    if (min > totalPrice) {
                        min = totalPrice;
                    } else if (max < totalPrice) {
                        max = totalPrice;
                    }
                    avg += totalPrice;
                }
            }
            if(filteredBookings.size() > 0) setReport(new Report(avg / filteredBookings.size(), min, max));
        }
    }

    private void processPeriod() {
        if (periodStart != null && periodEnd != null) {
            if (periodEnd.getTime() > periodStart.getTime()) {
                report = bookingEJB.getReportForPeriod(periodStart, periodEnd);
                bookings = bookingEJB.getBookingsForPeriod(periodStart, periodEnd);
                for (Booking booking : bookings) {
                    booking.setTrip(tripEJB.findTripEagerLoaded(booking.getTrip().getId()));
                }
            }
        }
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Booking> getFilteredBookings() {
        return filteredBookings;
    }

    public void setFilteredBookings(List<Booking> filteredBookings) {
        this.filteredBookings = filteredBookings;
    }
}
