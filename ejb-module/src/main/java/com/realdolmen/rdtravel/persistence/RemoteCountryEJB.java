package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Country;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteCountryEJB {
    List<Country> findCountrys();

    Country findCountryById(Long id);

    Country createCountry(Country country);

    void deleteCountry(Country country);

    Country updateCountry(Country country);
}
