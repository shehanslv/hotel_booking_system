package com.shehan.hotel_booking_system.controller;

import com.shehan.hotel_booking_system.DTO.HotelReviewRequestDTO;
import com.shehan.hotel_booking_system.DTO.HotelRoomRequestDTO;
import com.shehan.hotel_booking_system.entity.HotelReview;
import com.shehan.hotel_booking_system.entity.Room;
import com.shehan.hotel_booking_system.service.HotelReviewService;
import com.shehan.hotel_booking_system.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HotelRoomController {

    @Autowired
    private HotelRoomService hotelRoomService;
    @RequestMapping(method = RequestMethod.POST, value = "/hotel_room")
    public @ResponseBody Room saveHotelRoom(
            @RequestBody HotelRoomRequestDTO hotelRoomRequestDTO){

        return hotelRoomService.saveHotelRoom(hotelRoomRequestDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hotel_room/search_by_hotel/{id}")
    public @ResponseBody List<Room> getHotelRooms(@PathVariable("id") int id){
        return hotelRoomService.getHotelRoomList(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotel_room/{id}")
    public @ResponseBody Room updateHotelRoom(@PathVariable("id") int id,
                                           @RequestBody HotelRoomRequestDTO hotelRoomRequestDTO){
        return hotelRoomService.updateHotelRoom(hotelRoomRequestDTO,id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel_room/{id}")
    public @ResponseBody Map<String, String> deleteHotelRoom(@PathVariable("id") int id){
        return hotelRoomService.deleteHotelRoom(id);
    }

}
