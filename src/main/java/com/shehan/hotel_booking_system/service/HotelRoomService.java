package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.HotelReviewRequestDTO;
import com.shehan.hotel_booking_system.DTO.HotelRoomRequestDTO;
import com.shehan.hotel_booking_system.entity.HotelReview;
import com.shehan.hotel_booking_system.entity.Room;

import java.util.List;
import java.util.Map;

public interface HotelRoomService {

    Room saveHotelRoom(HotelRoomRequestDTO hotelRoomRequestDTO);

    List<Room> getHotelRoomList(int hotelId);

    Room updateHotelRoom(HotelRoomRequestDTO hotelRoomRequestDTO, int id);

    Map<String, String> deleteHotelRoom(int id);

}
