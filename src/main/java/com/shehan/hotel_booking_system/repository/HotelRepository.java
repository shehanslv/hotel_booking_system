package com.shehan.hotel_booking_system.repository;

import com.shehan.hotel_booking_system.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {




}
