package com.realdolmen.rdtravel.persistence;


import com.realdolmen.rdtravel.domain.Customer;
import org.junit.Test;

import javax.persistence.PersistenceException;

public class CustomerPersistenceTest extends PersistenceTest {

    private static final String TEST_CUSTOMER_NAME = "testCustomer";


    @Test
    public void customerCanBePersisted() {
        Customer customer = newCustomer();
        entityManager().persist(customer);
        assertNotNull(customer.getId());
    }

    @Test(expected = PersistenceException.class)
    public void noDuplicateNameAllowed() {
        Customer customer = newCustomer();
        entityManager().persist(customer);
        Customer duplicateNamedCustomer = newCustomer();
        entityManager().persist(duplicateNamedCustomer);
    }

    @Test
    public void customerCanBeUpdated() {
        Customer customer = newCustomer();
        entityManager().persist(customer);
        long id = customer.getId();
        flushAndClear();
        String newName = "newName";
        customer.setName(newName);
        entityManager().merge(customer);
        flushAndClear();
        assertEquals(newName, entityManager().find(Customer.class, id).getName());
    }

    @Test
    public void customerCanBeDeleted() {
        Customer customer = newCustomerForDelete();
        entityManager().persist(customer);
        long id = customer.getId();
        entityManager().remove(entityManager().merge(customer));
        assertNull(entityManager().find(Customer.class, id));
    }

    private Customer newCustomer() {
        return new Customer(TEST_CUSTOMER_NAME, "test");
    }

    private Customer newCustomerForDelete() {
        return new Customer("toBeDeleted", "test");
    }


}
