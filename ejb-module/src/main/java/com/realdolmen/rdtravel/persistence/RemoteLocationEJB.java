package com.realdolmen.rdtravel.persistence;

import javax.ejb.Remote;

/**
 * Created by OCPAX79 on 9/10/2015.
 */
@Remote
public interface RemoteLocationEJB {
    Long getLocationIdByName(String name);
}
