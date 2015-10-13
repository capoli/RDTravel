package com.realdolmen.rdtravel.integration;


import com.realdolmen.rdtravel.persistence.RemoteLocationEJB;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

public class LocationEJBTest extends RemoteIntegrationTest {
    private RemoteLocationEJB repo;
    private static final String locationName = "aruba";
    private static final long locationId = 1000;

    @Before
    public void init() throws NamingException {
        repo = lookup("ear-module-1.5/ejb-module-1.5/LocationEJB!com.realdolmen.rdtravel.persistence.RemoteLocationEJB");
    }

    @Test
    public void locationIdCanBeFoundByName() {
        assertEquals(locationId, repo.getLocationIdByName(locationName).longValue());
    }

    @Test
    public void allLocationsCanBeFound() {
        assertEquals(215, repo.findAllLocations().size());
    }
}
