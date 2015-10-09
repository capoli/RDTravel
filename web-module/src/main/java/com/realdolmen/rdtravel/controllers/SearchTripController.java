package com.realdolmen.rdtravel.controllers;

import com.google.gson.Gson;
import com.realdolmen.rdtravel.domain.*;
import com.realdolmen.rdtravel.persistence.*;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ConversationScoped
public class SearchTripController implements Serializable {
    @EJB
    CrudEJB crudEJB;

    @EJB
    TripEJB tripEJB;

    @EJB
    CustomerEJB customerEJB;

    @EJB
    BookingEJB bookingEJB;

    @EJB
    LocationEJB locationEJB;

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
    private Double totalPrice;
    private String creditCardNumber;
    private String creditCardDate;
//    private List<String> paymentTypes;
    private String selectedPaymentType;
    @ManagedProperty("#{param.locationName}")
    private String locationName;

    public void onSearchTripLoad() {
        selectedDestination = new Location();
        availableTrips = new ArrayList<>();
        conversation.begin();
        selectedDestinationId = locationEJB.getLocationIdByName(locationName);
    }

    public String startConversation() {
        selectedDestination = new Location();
        availableTrips = new ArrayList<>();
        conversation.begin();
        return "searchTrip.xhtml?faces-redirect=true";
    }

    public String confirmConversation() {
        Customer customerByName = customerEJB.findCustomerByName(getRequest().getUserPrincipal().getName().toLowerCase());
//        PaymentType currPaymentType = Enum.valueOf(PaymentType.class, selectedPaymentType);
        PaymentType currPaymentType = PaymentType.valueOf(selectedPaymentType);
        Booking newBook = new Booking(totalPrice, numberOfParticipants,
                currPaymentType, selectedTrip, customerByName);
        Booking tempBook = (Booking) crudEJB.create(newBook);
        conversation.end();
        return "/pages/customer/thankyou.xhtml?faces-redirect=true";
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
        return "/pages/customer/choiceConfirmation.xhtml?faces-redirect=true";
    }

    public String goToPayment() {
        long diffDates = selectedTrip.getPeriod().getPeriodEnd().getTime() - selectedTrip.getPeriod().getPeriodStart().getTime();
        double days = Math.floor(diffDates / (1000.0 * 60 * 60 * 24));
        totalPrice = days * selectedTrip.getPricePerDay();
        for (Flight flight : selectedTrip.getFlights()) {
            totalPrice += flight.getPrice();
        }
        totalPrice = (new BigDecimal(totalPrice)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return "/pages/customer/payment.xhtml?faces-redirect=true";
    }

    public void onPaymentTypeChange() {
        //TODO: implent
    }

    public void onRedirectToPayment() {
//        paymentTypes = bookingEJB.getPaymentTypes();
//        selectedPaymentType = paymentTypes.get(0);
        long diffDates = selectedTrip.getPeriod().getPeriodEnd().getTime() - selectedTrip.getPeriod().getPeriodStart().getTime();
        double days = Math.floor(diffDates / (1000.0 * 60 * 60 * 24));
        totalPrice = days * selectedTrip.getPricePerDay();
        for (Flight flight : selectedTrip.getFlights()) {
            totalPrice += flight.getPrice();
        }
        totalPrice = (new BigDecimal(totalPrice)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public List<Location> getAllLocations() {
        return tripEJB.findLocationsWithTrips();
    }

    public List<String> getAllLocationsAsGson() {
        Gson gson = new Gson();
        List<String> collect = tripEJB.findLocationnamesWithTrips().stream().map(gson::toJson).collect(Collectors.toList());
        return collect;
    }

    public List<String> getAllPaymentTypes() {
        return bookingEJB.getPaymentTypesAsString();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public void onTripChange() {
        if (selectedTripId == null) {
            selectedTripId = -1l;
        } else if (selectedTripId.equals(-1l)) {
            selectedTrip = null;
        } else {
            for (Trip current : availableTrips) {
                if (current.getId().equals(selectedTripId)) {
                    selectedTrip = current;
                    return;
                }
            }
        }
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

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardDate() {
        return creditCardDate;
    }

    public void setCreditCardDate(String creditCardDate) {
        this.creditCardDate = creditCardDate;
    }

    public String getSelectedPaymentType() {
        return selectedPaymentType;
    }

    public void setSelectedPaymentType(String selectedPaymentType) {
        this.selectedPaymentType = selectedPaymentType;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
