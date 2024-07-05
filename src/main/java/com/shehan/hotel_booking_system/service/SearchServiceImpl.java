package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.BookingRequestDTO;
import com.shehan.hotel_booking_system.DTO.SearchRequestByReviewLocationDTO;
import com.shehan.hotel_booking_system.entity.Booking;
import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.Room;
import com.shehan.hotel_booking_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    HotelRoomRepository hotelRoomRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelReviewRepository hotelReviewRepository;


    @Override
    public List<Hotel> searchByFilters(String location, String checkInDate, String checkOutDate) {

        List<Booking> bookingList =
        bookingRepository.findAll().stream()
                        .filter(n ->
                                !checkDateAvailability(n.getCheckInDate(),
                                        n.getCheckOutDate(),checkInDate)).toList();

        List<Hotel> hotelList =
        bookingList.stream()
                .filter(n -> n.getRoom().getHotel().getLocation().equalsIgnoreCase(location))
                .map(n -> n.getRoom().getHotel())
                .distinct()
                .collect(Collectors.toList());

        return hotelList;
    }

    @Override
    public List<Hotel> searchByReviewAndLocation(SearchRequestByReviewLocationDTO
                                                             searchRequestByReviewLocationDTO) {

        String review = searchRequestByReviewLocationDTO.getReview();
        String location = searchRequestByReviewLocationDTO.getLocation();

        return
        hotelReviewRepository.findAll().stream()
                .filter(n-> n.getReview().equalsIgnoreCase(review))
                .filter(n -> n.getHotel().getLocation().equalsIgnoreCase(location))
                .map(n-> n.getHotel())
                .distinct()
                .collect(Collectors.toList());

    }

    /*
    return true:
    check if the user input check-in date is same as a booked check-in date
    check if the user input check-in date is same as a booked check-out date
    check if the user input check-in date between booked check-in and check-out date
    */

    public boolean checkDateAvailability(LocalDate date1, LocalDate date2, String checkDate){

        boolean val =
                LocalDate.parse(checkDate).isEqual(date1)
                        | LocalDate.parse(checkDate).isEqual(date2)
                        | (LocalDate.parse(checkDate).isAfter(date1) &&
                        LocalDate.parse(checkDate).isBefore(date2));

        //if this is true - date is overlapped with check-in date and check-out date

        return val;
    }
}
