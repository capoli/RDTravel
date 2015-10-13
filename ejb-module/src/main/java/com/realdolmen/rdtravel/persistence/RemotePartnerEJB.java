package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Partner;

import javax.ejb.Remote;

@Remote
public interface RemotePartnerEJB {
    Partner findPartnerByName(String name);
}
