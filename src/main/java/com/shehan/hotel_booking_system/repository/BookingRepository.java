package com.shehan.hotel_booking_system.repository;

import com.shehan.hotel_booking_system.entity.Booking;
import com.shehan.hotel_booking_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {




}
