package com.realdolmen.rdtravel.persistence;


import com.realdolmen.rdtravel.domain.*;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlPersistenceToolTest {
    private XmlEJB xmlEJB;
    private static final Path testFilePath = Paths.get("testTripsCreation.xml");

    @Before
    public void init() {
        xmlEJB = new XmlEJB();
    }

    @Test
    public void testIfExportCreatesOutputStream() throws IOException, JAXBException {
        createTripsFile();
        checkIfTestFileExists();
        deleteTestFile();
    }

    @Test
    public void testIfImportHandlesInputStream() throws JAXBException, IOException {
        InputStream is = getClass().getResourceAsStream("/testTrips.xml");
        List<Trip> trips = xmlEJB.importTrips(is);
        assertEquals(2, trips.size());
        is.close();
    }

    private void deleteTestFile() throws IOException {
        Files.deleteIfExists(testFilePath);
    }

    private void checkIfTestFileExists() {
        assertTrue(Files.exists(testFilePath));
    }

    private Trip newTrip() {
        Trip trip = new Trip(499.99, hawai(), new Period(new Date(), new Date()));
        trip.addFlight(new Flight(new Date(), new Date(), 200, 99.99, london(), hawai(), 0.05, 120));
        trip.addFlight(new Flight(new Date(), new Date(), 200, 99.99, hawai(), london(), 0.05, 120));
        return trip;
    }

    private Trip otherTrip() {
        Trip trip = new Trip(399.99, tokyo(), new Period(new Date(), new Date()));
        trip.addFlight(new Flight(new Date(), new Date(), 100, 79.99, sydney(), tokyo(), 0.05, 120));
        trip.addFlight(new Flight(new Date(), new Date(), 100, 79.99, tokyo(), sydney(), 0.05, 120));
        return trip;
    }

    private Location hawai() {
        return new Location("Hawai", "HAW", new Continent("America", "AM"));
    }

    private Location london() {
        return new Location("London", "LON", new Continent("Europe", "EUR"));
    }

    private Location tokyo() {
        return new Location("Tokyo", "TOK", new Continent("Asia", "AS"));
    }

    private Location sydney() {
        return new Location("Sydney", "SYD", new Continent("Oceania", "OC"));
    }

    private void createTripsFile() throws IOException, JAXBException {
        deleteTestFile();
        TripGroup tripGroup = new TripGroup();
        tripGroup.addTrip(newTrip());
        tripGroup.addTrip(otherTrip());
        OutputStream os = new FileOutputStream(String.valueOf(testFilePath));
        xmlEJB.exportTrips(tripGroup, os);
        os.close();
    }

}
