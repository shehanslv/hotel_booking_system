package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.BookingRequestDTO;
import com.shehan.hotel_booking_system.DTO.HotelRoomRequestDTO;
import com.shehan.hotel_booking_system.entity.Booking;
import com.shehan.hotel_booking_system.entity.Room;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface BookingService {

    CompletableFuture<Booking> saveBooking(BookingRequestDTO bookingRequestDTO);

    List<Booking> getBookingsByHotel(int hotelId);

    Booking updateBooking(BookingRequestDTO bookingRequestDTO, int id);

    Map<String, String> deleteBooking(int id);

}
