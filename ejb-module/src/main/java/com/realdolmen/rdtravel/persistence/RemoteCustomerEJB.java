package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.Customer;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RemoteCustomerEJB {
    List<Customer> findCustomers();

    Customer findCustomerById(Long id);

    Customer findCustomerByName(String name);

    Customer findCustomerByEmail(String email);

    Customer createCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    Customer updateCustomer(Customer customer);
}
