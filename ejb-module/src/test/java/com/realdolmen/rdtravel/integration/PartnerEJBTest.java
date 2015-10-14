package com.realdolmen.rdtravel.integration;

import com.realdolmen.rdtravel.domain.Partner;
import com.realdolmen.rdtravel.persistence.RemotePartnerEJB;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

public class PartnerEJBTest extends RemoteIntegrationTest {
    private RemotePartnerEJB repo;
    private static final String partnerName = "partner";
    private static final long partnerId = 1002l;

    @Before
    public void init() throws NamingException {
        repo = lookup("ear-module-1.5/ejb-module-1.5/PartnerEJB!com.realdolmen.rdtravel.persistence.RemotePartnerEJB");
    }

    @Test
    public void partnerCanBeFoundByName() {
        Partner partner = repo.findPartnerByName(partnerName);
        assertEquals(partnerId, partner.getId().longValue());
    }
}
