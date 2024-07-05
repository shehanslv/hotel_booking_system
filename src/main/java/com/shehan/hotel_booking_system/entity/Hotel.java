package com.shehan.hotel_booking_system.entity;

import jakarta.persistence.*;

@Entity(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    private String hotelName;
    private String address;
    private String location;

    @Version
    private int version;

    public Hotel() {
    }

    public Hotel(String hotelName, String address, String location) {
        this.hotelName = hotelName;
        this.address = address;
        this.location = location;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
