package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.HotelReviewRequestDTO;
import com.shehan.hotel_booking_system.DTO.HotelRoomRequestDTO;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.HotelReview;
import com.shehan.hotel_booking_system.entity.Room;
import com.shehan.hotel_booking_system.exception.NoRecordExistedException;
import com.shehan.hotel_booking_system.repository.HotelRepository;
import com.shehan.hotel_booking_system.repository.HotelReviewRepository;
import com.shehan.hotel_booking_system.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelRoomServiceImpl implements HotelRoomService{

    @Autowired
    HotelRoomRepository hotelRoomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Room saveHotelRoom(HotelRoomRequestDTO hotelRoomRequestDTO) {

        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelRoomRequestDTO.getHotelId());

        if(optionalHotel.isPresent()){
            Hotel dbExistedHotel = optionalHotel.get();

            Room room = new Room();
            room.setHotel(dbExistedHotel);
            room.setRoomType(hotelRoomRequestDTO.getRoomType());
            room.setPrice(hotelRoomRequestDTO.getPrice());
            room.setMaxPersons(hotelRoomRequestDTO.getMaxPersons());
            room.setPriceUntil(LocalDate.parse(hotelRoomRequestDTO.getPriceUntilDate()));
            room.setSpecialFeatures(hotelRoomRequestDTO.getSpecialFeatures());

            return hotelRoomRepository.save(room);

        }else{
            throw new NoRecordExistedException("No existing hotel room records!");
        }

    }

    @Override
    public List<Room> getHotelRoomList(int hotelId) {

       return hotelRoomRepository.findAll().
                stream()
                .filter(n -> n.getHotel().getHotelId() == hotelId)
                .toList();
    }

    @Override
    public Room updateHotelRoom(HotelRoomRequestDTO hotelRoomRequestDTO,int id) {

        Optional<Room> optionalHotelRoom = hotelRoomRepository.findById(id);

        if(optionalHotelRoom.isPresent()){
            Room dbExistedHotelRoom = optionalHotelRoom.get();

            dbExistedHotelRoom.setRoomType(hotelRoomRequestDTO.getRoomType());
            dbExistedHotelRoom.setPrice(hotelRoomRequestDTO.getPrice());
            dbExistedHotelRoom.setMaxPersons(hotelRoomRequestDTO.getMaxPersons());
            dbExistedHotelRoom.setPriceUntil(LocalDate.parse(hotelRoomRequestDTO.getPriceUntilDate()));
            dbExistedHotelRoom.setSpecialFeatures(hotelRoomRequestDTO.getSpecialFeatures());

            return hotelRoomRepository.save(dbExistedHotelRoom);

        }else{
            return null;
        }

    }

    @Override
    public Map<String, String> deleteHotelRoom(int id) {

        Map<String, String> map = new HashMap<>();

        Optional<Room> optionalHotelRoom = hotelRoomRepository.findById(id);

        if(optionalHotelRoom.isPresent()){
            Room dbExistedHotelRoom = optionalHotelRoom.get();
            hotelRoomRepository.delete(dbExistedHotelRoom);

            map.put("status", "Success");
            map.put("message","Successfully deleted the Hotel room!");

        }else{

            map.put("status", "Error");
            map.put("message","There is no Hotel rooms saved for the given Hotel Id!");

        }
        return map;
    }
}
