package com.realdolmen.rdtravel.controllers;

import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Trip;
import com.realdolmen.rdtravel.persistence.CrudEJB;
import com.realdolmen.rdtravel.persistence.TripEJB;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Named
@ConversationScoped
public class SearchTripController implements Serializable {
    @EJB
    CrudEJB crudEJB;

    @EJB
    TripEJB tripEJB;

    @Inject
    private Conversation conversation;

    private Location selectedDestination;
    private Long selectedDestinationId;
    private Integer numberOfParticipants;
    private Date periodStart;
    private Date periodEnd;
    private List<Trip> availableTrips;
    private Trip selectedTrip;
    private Long selectedTripId;

    public String startConversation() {
        selectedDestination = new Location();
        availableTrips = new ArrayList<>();
        conversation.begin();
        return "searchTrip.xhtml?faces-redirect=true";
    }

    public String confirmConversation() {
        conversation.end();
        return "something";
    }

    public String searchForTrips() {
        selectedDestination = (Location) crudEJB.findById(Location.class, selectedDestinationId);
        availableTrips = tripEJB.findTripsForCriteria(selectedDestinationId, periodStart, periodEnd);
        return "availableTrips.xhtml?faces-redirect=true";
    }

    public String selectTrip() {
        return "summaryPage.xhtml?faces-redirect=true";
    }

    public String confirmSummary() {
        return "/pages/customer/payment.xhtml?faces-redirect=true";
    }

    public List<Location> getAllLocations() {
        return tripEJB.findLocationsWithTrips();
    }

    public void onTripChange() {
        if (selectedTripId != null) {
            for (Trip current : availableTrips) {
                if (current.getId().equals(selectedTripId)) {
                    selectedTrip = current;
                    return;
                }
            }
        } else selectedTripId = (long) 0;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public Location getSelectedDestination() {
        return selectedDestination;
    }

    public void setSelectedDestination(Location selectedDestination) {
        this.selectedDestination = selectedDestination;
    }

    public Long getSelectedDestinationId() {
        return selectedDestinationId;
    }

    public void setSelectedDestinationId(Long selectedDestinationId) {
        this.selectedDestinationId = selectedDestinationId;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
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

    public List<Trip> getAvailableTrips() {
        return availableTrips;
    }

    public void setAvailableTrips(List<Trip> availableTrips) {
        this.availableTrips = availableTrips;
    }

    public Trip getSelectedTrip() {
        return selectedTrip;
    }

    public void setSelectedTrip(Trip selectedTrip) {
        this.selectedTrip = selectedTrip;
    }

    public Long getSelectedTripId() {
        return selectedTripId;
    }

    public void setSelectedTripId(Long selectedTripId) {
        this.selectedTripId = selectedTripId;
    }
}
