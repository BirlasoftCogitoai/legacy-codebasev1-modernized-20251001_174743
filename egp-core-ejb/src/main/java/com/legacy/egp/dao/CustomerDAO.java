```java
package com.legacy.egp.dao;

import com.legacy.egp.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Data Access Object for Customers.
 */
@Stateless
public class CustomerDAO {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    /**
     * Finds a Customer by its ID.
     *
     * @param id the ID of the Customer
     * @return the Customer, or null if not found
     */
    public Customer findById(Long id) {
        try {
            return entityManager.find(Customer.class, id);
        } catch (NoResultException e) {
            logger.warn("Customer with ID {} not found.", id);
            return null;
        } catch (Exception e) {
            logger.error("Error finding Customer with ID {}", id, e);
            throw new RuntimeException("Error finding Customer", e);
        }
    }

    /**
     * Retrieves all Customers.
     *
     * @return a list of Customers
     */
    public List<Customer> findAll() {
        try {
            return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } catch (Exception e) {
            logger.error("Error retrieving all Customers", e);
            throw new RuntimeException("Error retrieving all Customers", e);
        }
    }

    /**
     * Saves or updates a Customer.
     *
     * @param customer the Customer to save or update
     */
    public void saveOrUpdate(Customer customer) {
        try {
            if (customer.getId() == null) {
                entityManager.persist(customer);
            } else {
                entityManager.merge(customer);
            }
        } catch (Exception e) {
            logger.error("Error saving or updating Customer", e);
            throw new RuntimeException("Error saving or updating Customer", e);
        }
    }

    /**
     * Deletes a Customer.
     *
     * @param customer the Customer to delete
     */
    public void delete(Customer customer) {
        try {
            entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
        } catch (Exception e) {
            logger.error("Error deleting Customer", e);
            throw new RuntimeException("Error deleting Customer", e);
        }
    }
}
```