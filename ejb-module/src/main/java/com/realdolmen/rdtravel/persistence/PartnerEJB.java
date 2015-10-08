package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Partner;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class PartnerEJB implements RemotePartnerEJB {

    @PersistenceContext
    private EntityManager em;

    /**
     * @param name the name of the partner
     * @return Partner or null when no partner found
     */
    @Override
    public Partner findPartnerByName(String name) {
        try {
            return em.createQuery("select p from Partner p where p.name = :name", Partner.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
