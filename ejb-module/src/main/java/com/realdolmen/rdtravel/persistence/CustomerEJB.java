package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Customer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class CustomerEJB implements RemoteCustomerEJB {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findCustomers() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    /**
     * Returns null when no customer is found.
     */
    public Customer findCustomerByName(String name) {
        try {
            return em.createQuery("select c from Customer c where c.name = :name", Customer.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    /**
     * Returns null when no customer is found.
     */
    public Customer findCustomerByEmail(String email) {
        try {
            return em.createQuery("select c from Customer c where c.email = :email", Customer.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        em.remove(updateCustomer(customer));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return em.merge(customer);
    }
}
