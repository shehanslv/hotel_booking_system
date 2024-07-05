package com.shehan.hotel_booking_system.repository;

import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer("Shehan",
                "No.12, Colombo", "123455");
        Customer savedCustomer = customerRepository.save(customer);
        assertNotNull(savedCustomer);
        assertEquals("Shehan", savedCustomer.getCustomerName());
    }

    @Test
    public void testGetCustomer() {
        Customer customer = customerRepository.save(new Customer("Shehan",
                "No.12, Colombo", "123455"));
        Customer fetchedCustomer = customerRepository.
                findById(customer.getCustomerId()).orElse(null);
        assertNotNull(fetchedCustomer);
        assertEquals(customer.getCustomerName(), fetchedCustomer.getCustomerName());
    }

    @Test
    public void testGetCustomerList() {
        customerRepository.save(new Customer("Shehan",
                "No.12, Colombo", "123455"));

        customerRepository.save(new Customer("Kamal",
                "No.12, Kandy", "9768567"));
        List<Customer> customerList = customerRepository.findAll();

        assertNotNull(customerList);
        assertEquals(2, customerList.size());
    }

    @Test
    public void testDeleteCustomer() {

        Customer customer =
                customerRepository.save(new Customer("Shehan",
                        "No.12, Colombo", "123455"));

        customerRepository.deleteById(customer.getCustomerId());

        Customer deletedCustomer =
                customerRepository.findById(customer.getCustomerId()).orElse(null);

        assertNull(deletedCustomer);

    }

    @Test
    public void testUpdateCustomer() {
        Customer savedCustomer =
                customerRepository.save(new Customer("Shehan",
                        "No.12, Colombo", "123455"));

        savedCustomer.setCustomerName("Nimal");
        customerRepository.save(savedCustomer);

        Customer updatedCustomer = customerRepository.
                findById(savedCustomer.getCustomerId()).orElse(null);

        assertNotNull(updatedCustomer);
        assertEquals("Nimal", updatedCustomer.getCustomerName());
    }
}