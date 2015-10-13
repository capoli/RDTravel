package com.realdolmen.rdtravel.integration;

import com.realdolmen.rdtravel.domain.Customer;
import com.realdolmen.rdtravel.persistence.RemoteCustomerEJB;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJBTransactionRolledbackException;
import javax.naming.NamingException;
import java.util.List;

public class CustomerEJBTest extends RemoteIntegrationTest {
    private RemoteCustomerEJB repo;
    private static final long customerId = 1000;
    private static final String customerName = "customer";
    private static final String customerEmail = "customer@hotmail.com";
    private static final long customerWithoutBookingsId = 1003;

    @Before
    public void init() throws NamingException {
        repo = lookup("ear-module-1.5/ejb-module-1.5/CustomerEJB!com.realdolmen.rdtravel.persistence.RemoteCustomerEJB");
    }

    @Test
    public void allCustomersCanBeFound() {
        List<Customer> customers = repo.findCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    public void customerCanBeFoundById() {
        assertNotNull(repo.findCustomerById(customerId));
    }

    @Test
    public void customerCanBeFoundByName() {
        assertNotNull(repo.findCustomerByName(customerName));
    }

    @Test
    public void customerCanBeFoundByEmail() {
        assertNotNull(repo.findCustomerByEmail("customer@hotmail.com"));
    }

    @Test
    public void customerCanBeDeleted() {
        Customer customer = repo.findCustomerById(customerWithoutBookingsId);
        repo.deleteCustomer(customer);
        assertNull(repo.findCustomerById(customerWithoutBookingsId));
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void customerWithBookingsCanNotBeDeleted() {
        Customer customer = repo.findCustomerById(customerId);
        repo.deleteCustomer(customer);
        assertNull(repo.findCustomerById(customerId));
    }

    @Test
    public void customerCanBeUpdated() {
        Customer customer = repo.findCustomerById(customerId);
        String newName = "newName";
        customer.setName(newName);
        repo.updateCustomer(customer);
        customer = repo.findCustomerById(customerId);
        assertEquals(newName, customer.getName());
    }

    @Test
    public void customerCanBeCreated() {
        Customer customer = new Customer("repoCustomer", "test123", "repoCustomer@hotmail.com");
        repo.createCustomer(customer);
        assertNotNull(customer.getId());
    }
}
