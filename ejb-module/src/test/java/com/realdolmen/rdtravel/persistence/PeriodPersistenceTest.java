package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Period;
import org.junit.Test;

import java.util.Date;

public class PeriodPersistenceTest extends DataSetPersistenceTest {
    @Test
    public void periodCanBePersisted() {
        Period period = new Period(new Date(), new Date());
        entityManager().persist(period);
        entityManager().flush();
        entityManager().refresh(period);
        assertNotNull(period);
        assertNotNull(period.getId());
    }

    @Test
    public void periodCanBeFound() {
        Period period = entityManager().find(Period.class, 1000l);
        assertNotNull(period);
        assertEquals(1000l, (long) period.getId());
    }
}
