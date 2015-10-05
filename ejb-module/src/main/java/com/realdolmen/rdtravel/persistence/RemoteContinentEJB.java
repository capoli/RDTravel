package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Continent;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteContinentEJB  {
    List<Continent> findContinents();

    Continent findContinentById(Long id);

    Continent createContinent(Continent continent);

    void deleteContinent(Continent continent);

    Continent updateContinent(Continent continent);
}
