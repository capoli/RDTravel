package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Continent;
import com.realdolmen.rdtravel.domain.Location;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by OCPAX79 on 6/10/2015.
 */
public class LocationPersistenceTest extends DataSetPersistenceTest {
    @Rule
    public ExpectedException expector = ExpectedException.none();

    @Test
    public void locationCanBePersisted() {
//        Continent continent = entityManager().find(Continent.class, 1001l);
        Continent continent = new Continent("Europe", "eur");
        Location location = new Location("TestLocation", "tln", continent);
        continent.addLocation(location);
        entityManager().persist(continent);
        entityManager().flush();
        entityManager().refresh(location);
        entityManager().refresh(continent);
        assertNotNull(location);
        assertNotNull(continent);
        assertTrue("location should have an id", continent.getId() != null);
        assertEquals(1, continent.getLocations().size());
    }

    @Test
    public void locationCanBeFound() {
        Location location = entityManager().find(Location.class, 1000l);
        assertNotNull(location);
        assertEquals("ABW", location.getCode());
        assertNotNull(location.getContinent());
    }
}
