package com.shehan.hotel_booking_system.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "hotel_review")
public class HotelReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelReviewId;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    private String review;

    private LocalDate createdDate;

    @Version
    private int version;

    public HotelReview() {
    }

    public HotelReview(Hotel hotel, String review, LocalDate createdDate) {
        this.hotel = hotel;
        this.review = review;
        this.createdDate = createdDate;
    }

    public int getHotelReviewId() {
        return hotelReviewId;
    }

    public void setHotelReviewId(int hotelReviewId) {
        this.hotelReviewId = hotelReviewId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
