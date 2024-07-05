package com.shehan.hotel_booking_system.controller;

import com.shehan.hotel_booking_system.DTO.BookingRequestDTO;
import com.shehan.hotel_booking_system.DTO.SearchRequestByReviewLocationDTO;
import com.shehan.hotel_booking_system.DTO.SearchRequestDTO;
import com.shehan.hotel_booking_system.entity.Booking;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.service.BookingService;
import com.shehan.hotel_booking_system.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;
    @RequestMapping(method = RequestMethod.POST, value = "/search_hotels_filter1")
    public @ResponseBody List<Hotel> searchHotelsByLocationAndCheckinAndCheckoutDates(
            @RequestBody SearchRequestDTO searchRequestDTO){

        List<Hotel> hotelList = searchService.searchByFilters(searchRequestDTO.getLocation(),
                searchRequestDTO.getCheckInDate(),searchRequestDTO.getCheckOutDate());

        return hotelList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search_hotels_filter2")
    public @ResponseBody List<Hotel> searchHotelsByReviewAndLocation(
            @RequestBody SearchRequestByReviewLocationDTO searchRequestByReviewLocationDTO){

        List<Hotel> hotelList = searchService.
                searchByReviewAndLocation(searchRequestByReviewLocationDTO);

        return hotelList;
    }


}
