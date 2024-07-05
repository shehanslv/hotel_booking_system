package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.exception.NoRecordExistedException;
import com.shehan.hotel_booking_system.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getHotelList() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotel(Hotel hotel,int id) {

        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if(optionalHotel.isPresent()){
            Hotel dbExistedHotel = optionalHotel.get();

            dbExistedHotel.setHotelName(hotel.getHotelName());
            dbExistedHotel.setAddress(hotel.getAddress());
            dbExistedHotel.setLocation(hotel.getLocation());

            return hotelRepository.save(dbExistedHotel);

        }else{
            throw new NoRecordExistedException("No existing hotel records!");
        }

    }

    @Override
    public Map<String, String> deleteHotel(int id) {

        Map<String, String> map = new HashMap<>();

        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if(optionalHotel.isPresent()){
            Hotel dbExistedHotel = optionalHotel.get();
            hotelRepository.delete(dbExistedHotel);

            map.put("status", "Success");
            map.put("message","Successfully deleted the Hotel details!");

        }else{

            map.put("status", "Error");
            map.put("message","There is no hotel details saved for the given Id!");

        }
        return map;
    }
}
