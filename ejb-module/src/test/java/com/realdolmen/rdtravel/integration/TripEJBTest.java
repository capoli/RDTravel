package com.realdolmen.rdtravel.integration;

import com.realdolmen.rdtravel.domain.Location;
import com.realdolmen.rdtravel.domain.Period;
import com.realdolmen.rdtravel.domain.Trip;
import com.realdolmen.rdtravel.persistence.RemoteTripEJB;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TripEJBTest extends RemoteIntegrationTest {
    private RemoteTripEJB repo;
    private static final int numberOfLocationsWithTrips = 69;
    private static final long iraqId = 1091l;

    @Before
    public void init() throws NamingException {
        repo = lookup("ear-module-1.5/ejb-module-1.5/TripEJB!com.realdolmen.rdtravel.persistence.RemoteTripEJB");
    }

    @Test
    public void testIfLocationsWithTripsCanBeFound() {
        List<Location> locations = repo.findLocationsWithTrips();
        assertEquals(numberOfLocationsWithTrips, locations.size());
    }

    @Test
    public void testIfLocationNamesWithTripsCanBeFound() {
        List<String> locationNamesWithTrips = repo.findLocationNamesWithTrips();
        assertEquals(numberOfLocationsWithTrips, locationNamesWithTrips.size());
    }

    @Test
    public void testIfTripCanBeFoundForCriteria() {
        List<Date> dates = february4thTo19th2016();
        List<Trip> trips = repo.findTripsForCriteria(iraqId, dates.get(0), dates.get(1), 1);
        assertEquals(1, trips.size());
        Trip iraqTrip = trips.get(0);
        assertEquals(iraqId, iraqTrip.getDestination().getId().longValue());
        checkIfTripPeriodBetweenDates(iraqTrip.getPeriod(), dates);

    }

    private List<Date> february4thTo19th2016() {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.FEBRUARY, 4);
        dates.add(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        dates.add(calendar.getTime());
        return dates;
    }

    private void checkIfTripPeriodBetweenDates(Period period, List<Date> dates) {
        assertTrue(period.getPeriodStart().after(dates.get(0)));
        assertTrue(period.getPeriodEnd().before(dates.get(1)));
    }


}
