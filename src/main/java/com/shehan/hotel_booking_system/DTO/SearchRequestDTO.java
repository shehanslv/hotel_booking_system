package com.shehan.hotel_booking_system.DTO;

import lombok.Getter;

@Getter
public class SearchRequestDTO {
    
    private String location;
    private String checkInDate;
    private String checkOutDate;

}
