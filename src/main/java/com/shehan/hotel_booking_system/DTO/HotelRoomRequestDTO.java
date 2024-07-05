package com.shehan.hotel_booking_system.DTO;

import lombok.Getter;

@Getter
public class HotelRoomRequestDTO {

    private int hotelId;
    private String roomType;
    private String specialFeatures;
    private double price;
    private String priceUntilDate;
    private int maxPersons;




}
