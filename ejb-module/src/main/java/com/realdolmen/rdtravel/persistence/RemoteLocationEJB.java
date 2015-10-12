package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Location;
import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteLocationEJB {
    Long getLocationIdByName(String name);
    List<Location> findAllLocations();
}