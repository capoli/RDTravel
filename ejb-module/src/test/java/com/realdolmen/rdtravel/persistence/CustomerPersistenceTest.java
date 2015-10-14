package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Customer;
import org.junit.Test;

import javax.persistence.PersistenceException;

public class CustomerPersistenceTest extends PersistenceTest {
    private static final String TEST_CUSTOMER_PASSWORD = "test123";


    @Test
    public void customerCanBePersisted() {
        Customer customer = new Customer("test1", TEST_CUSTOMER_PASSWORD, "test1@hotmail.com");
        entityManager().persist(customer);
        assertNotNull(customer.getId());
    }

    @Test(expected = PersistenceException.class)
    public void noDuplicateNameAllowed() {
        Customer customer = new Customer("test2", TEST_CUSTOMER_PASSWORD, "test2@hotmail.com");
        entityManager().persist(customer);
        Customer duplicateNamedCustomer = new Customer("test2", TEST_CUSTOMER_PASSWORD, "test2r@hotmail.com");
        entityManager().persist(duplicateNamedCustomer);
    }

    @Test(expected = PersistenceException.class)
    public void noDuplicateEmailAllowed() {
        Customer customer = new Customer("test3", TEST_CUSTOMER_PASSWORD, "test3@hotmail.com");
        entityManager().persist(customer);
        Customer duplicateEmailCustomer = new Customer("test3r", TEST_CUSTOMER_PASSWORD, "test3@hotmail.com");
        entityManager().persist(duplicateEmailCustomer);
    }

    @Test
    public void customerCanBeUpdated() {
        Customer customer = new Customer("test4", TEST_CUSTOMER_PASSWORD, "test4@hotmail.com");
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
        Customer customer = new Customer("test5", TEST_CUSTOMER_PASSWORD, "test5@hotmail.com");
        entityManager().persist(customer);
        long id = customer.getId();
        entityManager().remove(entityManager().merge(customer));
        assertNull(entityManager().find(Customer.class, id));
    }

    @Test(expected = PersistenceException.class)
    public void testIfErrorOnMissingName() {
        Customer customer = new Customer(null, TEST_CUSTOMER_PASSWORD, "test6@hotmail.com");
        entityManager().persist(customer);
    }

    @Test(expected = PersistenceException.class)
    public void testIfErrorOnMissingPassword() {
        Customer customer = new Customer("test7", null, "test7@hotmail.com");
        entityManager().persist(customer);
    }

    @Test(expected = PersistenceException.class)
    public void testIfErrorOnMissingEmail() {
        Customer customer = new Customer("test8", TEST_CUSTOMER_PASSWORD, null);
        entityManager().persist(customer);
    }
}
