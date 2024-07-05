package com.shehan.hotel_booking_system.controller;

import com.shehan.hotel_booking_system.DTO.BookingRequestDTO;
import com.shehan.hotel_booking_system.DTO.HotelRoomRequestDTO;
import com.shehan.hotel_booking_system.entity.Booking;
import com.shehan.hotel_booking_system.entity.Room;
import com.shehan.hotel_booking_system.service.BookingService;
import com.shehan.hotel_booking_system.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @RequestMapping(method = RequestMethod.POST, value = "/booking")
    public @ResponseBody CompletableFuture<Booking> saveBooking(
            @RequestBody BookingRequestDTO bookingRequestDTO){

        return bookingService.saveBooking(bookingRequestDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/booking/search_by_hotel/{id}")
    public @ResponseBody List<Booking> getHotelRooms(@PathVariable("id") int id){
        return bookingService.getBookingsByHotel(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/booking/{id}")
    public @ResponseBody Booking updateBooking(@PathVariable("id") int id,
                                           @RequestBody BookingRequestDTO bookingRequestDTO){
        return bookingService.updateBooking(bookingRequestDTO,id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/booking/{id}")
    public @ResponseBody Map<String, String> deleteBooking(@PathVariable("id") int id){
        return bookingService.deleteBooking(id);
    }

}
