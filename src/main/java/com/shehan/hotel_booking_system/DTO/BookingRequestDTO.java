package com.shehan.hotel_booking_system.DTO;

import lombok.Getter;
@Getter
public class BookingRequestDTO {
    
    private int roomId;
    private int customerId;
    private String checkInDate;
    private String checkOutDate;

}
