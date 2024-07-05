package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.exception.NoRecordExistedException;
import com.shehan.hotel_booking_system.repository.CustomerRepository;
import com.shehan.hotel_booking_system.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer,int id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isPresent()){
            Customer dbExistedCustomer = optionalCustomer.get();

            dbExistedCustomer.setCustomerName(customer.getCustomerName());
            dbExistedCustomer.setCustomerAddress(customer.getCustomerAddress());
            dbExistedCustomer.setCustomerContactNumber(customer.getCustomerContactNumber());

            return customerRepository.save(dbExistedCustomer);

        }else{
            throw new NoRecordExistedException("No existing customer records!");
        }

    }

    @Override
    public Map<String, String> deleteCustomer(int id) {

        Map<String, String> map = new HashMap<>();

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isPresent()){
            Customer dbExistedCustomer = optionalCustomer.get();
            customerRepository.delete(dbExistedCustomer);

            map.put("status", "Success");
            map.put("message","Successfully deleted the Customer details!");

        }else{

            map.put("status", "Error");
            map.put("message","There is no Customer details saved for the given Id!");

        }
        return map;
    }
}
