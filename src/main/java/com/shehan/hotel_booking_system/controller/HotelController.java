package com.shehan.hotel_booking_system.controller;

import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @RequestMapping(method = RequestMethod.POST, value = "/hotel")
    public @ResponseBody ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.saveHotel(hotel), HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/hotel")
    public @ResponseBody List<Hotel> getHotel(){
        return hotelService.getHotelList();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotel/{id}")
    public @ResponseBody Hotel updateHotel(@PathVariable("id") int id,
                                           @RequestBody Hotel hotel){
        return hotelService.updateHotel(hotel,id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel/{id}")
    public @ResponseBody Map<String, String> deleteHotel(@PathVariable("id") int id){
        return hotelService.deleteHotel(id);
    }

}
