package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Location;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by TGIAX39 on 8/10/2015.
 */
@Remote
public interface RemoteLocationEJB {
    List<Location> findAllLocations();
}
