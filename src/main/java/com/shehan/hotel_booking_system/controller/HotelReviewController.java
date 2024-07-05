package com.shehan.hotel_booking_system.controller;

import com.shehan.hotel_booking_system.DTO.HotelReviewRequestDTO;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.HotelReview;
import com.shehan.hotel_booking_system.service.HotelReviewService;
import com.shehan.hotel_booking_system.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HotelReviewController {

    @Autowired
    private HotelReviewService hotelReviewService;
    @RequestMapping(method = RequestMethod.POST, value = "/hotel_review")
    public @ResponseBody HotelReview saveHotel(
            @RequestBody HotelReviewRequestDTO hotelReviewRequestDTO){

        return hotelReviewService.saveHotelReview(hotelReviewRequestDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hotel_review/search_by_hotel/{id}")
    public @ResponseBody List<HotelReview> getHotelReviews(@PathVariable("id") int id){
        return hotelReviewService.getHotelReviewList(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotel_review/{id}")
    public @ResponseBody HotelReview updateHotel(@PathVariable("id") int id,
                                           @RequestBody String review){
        return hotelReviewService.updateHotelReview(review,id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel_review/{id}")
    public @ResponseBody Map<String, String> deleteHotelReview(@PathVariable("id") int id){
        return hotelReviewService.deleteHotelReview(id);
    }

}
