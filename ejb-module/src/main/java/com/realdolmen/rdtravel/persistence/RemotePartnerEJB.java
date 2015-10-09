package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Partner;

/**
 * Created by TGIAX39 on 8/10/2015.
 */
public interface RemotePartnerEJB {
    Partner findPartnerByName(String name);
}
