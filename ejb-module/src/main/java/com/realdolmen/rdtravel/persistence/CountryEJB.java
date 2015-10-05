package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Country;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class CountryEJB implements RemoteCountryEJB {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Country> findCountrys() {
        return em.createQuery("select t from Country t", Country.class).getResultList();
    }

    @Override
    public Country findCountryById(Long id) {
        return em.find(Country.class, id);
    }

    @Override
    public Country createCountry(Country country) {
        em.persist(country);
        return country;
    }

    @Override
    public void deleteCountry(Country country) {
        em.remove(updateCountry(country));
    }

    @Override
    public Country updateCountry(Country country) {
        return em.merge(country);
    }

}
