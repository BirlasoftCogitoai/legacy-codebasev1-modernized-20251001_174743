```java
package com.legacy.egp.ejb;

import com.legacy.egp.entity.Customer;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * Modernized Customer Bean Local Interface
 */
@Local
public interface CustomerBeanLocal {

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Optional<Customer> getCustomer(Long customerId);

    Optional<Customer> getCustomerByNumber(String customerNumber);

    List<Customer> searchCustomersByLastName(String lastName);

    List<Customer> getAllCustomers();
}
```