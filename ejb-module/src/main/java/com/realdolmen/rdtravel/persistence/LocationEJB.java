package com.realdolmen.rdtravel.persistence;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import com.realdolmen.rdtravel.domain.Location;
import java.util.List;

@Stateless
@LocalBean
public class LocationEJB implements RemoteLocationEJB {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long getLocationIdByName(String name) {
        try {
            return em.createQuery("select l.id from Location l where l.name = :name", Long.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
	@Override
    public List<Location> findAllLocations() {
        return em.createQuery("select l from Location l", Location.class).getResultList();
    }
}
