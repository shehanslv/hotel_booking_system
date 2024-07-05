package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.HotelReviewRequestDTO;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.HotelReview;

import java.util.List;
import java.util.Map;

public interface HotelReviewService {

    HotelReview saveHotelReview(HotelReviewRequestDTO hotelReviewRequestDTO);

    List<HotelReview> getHotelReviewList(int hotelId);

    HotelReview updateHotelReview(String review, int id);

    Map<String, String> deleteHotelReview(int id);

}
