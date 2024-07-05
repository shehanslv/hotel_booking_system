package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.SearchRequestByReviewLocationDTO;
import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;

import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Hotel> searchByFilters(String location, String checkInDate,
                                String checkOutDate);

    List<Hotel> searchByReviewAndLocation(SearchRequestByReviewLocationDTO searchRequestByReviewLocationDTO);


}
