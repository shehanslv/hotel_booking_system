package com.shehan.hotel_booking_system.repository;

import com.shehan.hotel_booking_system.entity.HotelReview;
import com.shehan.hotel_booking_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomRepository extends JpaRepository<Room,Integer> {




}
