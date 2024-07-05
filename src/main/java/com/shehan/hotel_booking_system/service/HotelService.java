package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.entity.Hotel;

import java.util.List;
import java.util.Map;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getHotelList();

    Hotel updateHotel(Hotel hotel, int id);

    Map<String, String> deleteHotel(int id);

}
