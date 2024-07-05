package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.entity.Customer;


import java.util.List;
import java.util.Map;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> getCustomerList();

    Customer updateCustomer(Customer customer, int id);

    Map<String, String> deleteCustomer(int id);

}
