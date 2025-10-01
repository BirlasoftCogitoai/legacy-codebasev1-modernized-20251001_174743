```java
package com.legacy.egp.ejb;

import com.legacy.egp.dao.CustomerDAO;
import com.legacy.egp.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.Optional;

/**
 * Modernized Customer Session Bean - EJB 3.x
 * Provides business logic for customer operations
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CustomerBean implements CustomerBeanLocal, CustomerBeanRemote {

    private static final Logger logger = LoggerFactory.getLogger(CustomerBean.class);

    @EJB
    private CustomerDAO customerDAO;

    @Override
    public Customer createCustomer(Customer customer) {
        try {
            return customerDAO.save(customer);
        } catch (Exception e) {
            logger.error("Error creating customer: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating customer", e);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        try {
            return customerDAO.update(customer);
        } catch (Exception e) {
            logger.error("Error updating customer: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating customer", e);
        }
    }

    @Override
    public Optional<Customer> getCustomer(Long customerId) {
        try {
            return customerDAO.findById(customerId);
        } catch (Exception e) {
            logger.error("Error fetching customer by ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching customer by ID", e);
        }
    }

    @Override
    public Optional<Customer> getCustomerByNumber(String customerNumber) {
        try {
            return customerDAO.findByNumber(customerNumber);
        } catch (Exception e) {
            logger.error("Error fetching customer by number: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching customer by number", e);
        }
    }

    @Override
    public List<Customer> searchCustomersByLastName(String lastName) {
        try {
            return customerDAO.findByLastName(lastName);
        } catch (Exception e) {
            logger.error("Error searching customers by last name: {}", e.getMessage(), e);
            throw new RuntimeException("Error searching customers by last name", e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            return customerDAO.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all customers: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching all customers", e);
        }
    }
}
```