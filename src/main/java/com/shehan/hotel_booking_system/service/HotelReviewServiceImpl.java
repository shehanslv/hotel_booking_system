package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.HotelReviewRequestDTO;
import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.HotelReview;
import com.shehan.hotel_booking_system.exception.NoRecordExistedException;
import com.shehan.hotel_booking_system.repository.HotelRepository;
import com.shehan.hotel_booking_system.repository.HotelReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelReviewServiceImpl implements HotelReviewService{

    @Autowired
    HotelReviewRepository hotelReviewRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public HotelReview saveHotelReview(HotelReviewRequestDTO hotelReviewRequestDTO) {

        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelReviewRequestDTO.getHotelId());

        if(optionalHotel.isPresent()){
            Hotel dbExistedHotel = optionalHotel.get();

            HotelReview hotelReview = new HotelReview();
            hotelReview.setHotel(dbExistedHotel);
            hotelReview.setReview(hotelReviewRequestDTO.getReview());
            hotelReview.setCreatedDate(LocalDate.now());

            return hotelReviewRepository.save(hotelReview);

        }else{
            throw new NoRecordExistedException("No existing hotel review to update!");
        }

    }

    @Override
    public List<HotelReview> getHotelReviewList(int hotelId) {

       return hotelReviewRepository.findAll().
                stream()
                .filter(n -> n.getHotel().getHotelId() == hotelId)
                .toList();
    }

    @Override
    public HotelReview updateHotelReview(String review,int id) {

        Optional<HotelReview> optionalHotelReview = hotelReviewRepository.findById(id);

        if(optionalHotelReview.isPresent()){
            HotelReview dbExistedHotelReview = optionalHotelReview.get();

            dbExistedHotelReview.setReview(review);

            return hotelReviewRepository.save(dbExistedHotelReview);

        }else{
            return null;
        }

    }

    @Override
    public Map<String, String> deleteHotelReview(int id) {

        Map<String, String> map = new HashMap<>();

        Optional<HotelReview> optionalHotelReview = hotelReviewRepository.findById(id);

        if(optionalHotelReview.isPresent()){
            HotelReview dbExistedHotelReview = optionalHotelReview.get();
            hotelReviewRepository.delete(dbExistedHotelReview);

            map.put("status", "Success");
            map.put("message","Successfully deleted the Hotel review!");

        }else{

            map.put("status", "Error");
            map.put("message","There is no Hotel reviews saved for the given Hotel Id!");

        }
        return map;
    }
}
