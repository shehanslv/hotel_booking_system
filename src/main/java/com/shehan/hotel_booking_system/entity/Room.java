package com.shehan.hotel_booking_system.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    private String roomType;
    private String specialFeatures;
    private double price;
    private LocalDate priceUntil;

    private int maxPersons;

    @Version
    private int version;

    public Room() {
    }

    public Room(Hotel hotel, String roomType, String specialFeatures, double price, LocalDate priceUntil, int maxPersons) {
        this.hotel = hotel;
        this.roomType = roomType;
        this.specialFeatures = specialFeatures;
        this.price = price;
        this.priceUntil = priceUntil;
        this.maxPersons = maxPersons;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getPriceUntil() {
        return priceUntil;
    }

    public void setPriceUntil(LocalDate priceUntil) {
        this.priceUntil = priceUntil;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }
}
