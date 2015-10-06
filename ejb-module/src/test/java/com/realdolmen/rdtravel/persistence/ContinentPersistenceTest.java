package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Continent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ContinentPersistenceTest extends DataSetPersistenceTest {
    @Rule
    public ExpectedException expector = ExpectedException.none();

    @Test
    public void continentCanBePersisted() {
        Continent continent = new Continent("Europe", "eur");
        entityManager().persist(continent);
        entityManager().flush();
        entityManager().refresh(continent);
        assertTrue("continent should have an id", continent.getId() != null);
    }

    @Test
    public void continentCanBeFound() {
        Continent continent = entityManager().find(Continent.class, 1001l);
        assertTrue(continent != null);
        assertEquals("SAS", continent.getCode());
    }
}
