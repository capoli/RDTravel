package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Report;
import com.realdolmen.rdtravel.persistence.BookingEJB;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class BookingReportController implements Serializable{
    @EJB
    private BookingEJB bookingEJB;

    private Date periodStart;
    private Date periodEnd;
    private Report report;

    public void onPeriodChange(SelectEvent event) {
        processPeriod();
    }

    private void processPeriod() {
        if(periodStart != null && periodEnd != null) {
            if(periodEnd.getTime() > periodStart.getTime()) {
                report = bookingEJB.getReportForPeriod(periodStart, periodEnd);
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
}
