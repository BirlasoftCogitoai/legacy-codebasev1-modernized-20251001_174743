package com.legacy.egp.ejb;

import com.legacy.egp.entity.Customer;

import javax.ejb.Remote;
import java.util.List;

/**
 * Customer Bean Remote Interface
 * Provides remote access to customer-related operations
 */
@Remote
public interface CustomerBeanRemote {

    /**
     * Creates a new customer
     * @param customer the customer to create
     * @return the created customer
     */
    Customer createCustomer(Customer customer);

    /**
     * Updates an existing customer
     * @param customer the customer to update
     * @return the updated customer
     */
    Customer updateCustomer(Customer customer);

    /**
     * Retrieves a customer by ID
     * @param customerId the ID of the customer to retrieve
     * @return the retrieved customer
     */
    Customer getCustomer(Long customerId);

    /**
     * Retrieves a customer by customer number
     * @param customerNumber the customer number of the customer to retrieve
     * @return the retrieved customer
     */
    Customer getCustomerByNumber(String customerNumber);

    /**
     * Searches for customers by last name
     * @param lastName the last name to search for
     * @return a list of customers matching the last name
     */
    List<Customer> searchCustomersByLastName(String lastName);
}