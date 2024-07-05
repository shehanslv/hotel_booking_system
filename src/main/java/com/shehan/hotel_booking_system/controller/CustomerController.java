package com.shehan.hotel_booking_system.controller;

import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.service.CustomerService;
import com.shehan.hotel_booking_system.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @RequestMapping(method = RequestMethod.POST, value = "/customer")
    public @ResponseBody Customer saveCustomer(@RequestBody Customer hotel){

        return customerService.saveCustomer(hotel);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer")
    public @ResponseBody List<Customer> getCustomer(){

        return customerService.getCustomerList();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customer/{id}")
    public @ResponseBody Customer updateCustomer(@PathVariable("id") int id,
                                           @RequestBody Customer customer){
        return customerService.updateCustomer(customer,id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
    public @ResponseBody Map<String, String> deleteCustomer(@PathVariable("id") int id){
        return customerService.deleteCustomer(id);
    }

}
