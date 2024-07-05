package com.shehan.hotel_booking_system.service;

import com.shehan.hotel_booking_system.DTO.BookingRequestDTO;
import com.shehan.hotel_booking_system.DTO.HotelRoomRequestDTO;
import com.shehan.hotel_booking_system.entity.Booking;
import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.Room;
import com.shehan.hotel_booking_system.exception.NoRecordExistedException;
import com.shehan.hotel_booking_system.repository.BookingRepository;
import com.shehan.hotel_booking_system.repository.CustomerRepository;
import com.shehan.hotel_booking_system.repository.HotelRepository;
import com.shehan.hotel_booking_system.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    HotelRoomRepository hotelRoomRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Async
    @Override
    public CompletableFuture<Booking> saveBooking(BookingRequestDTO bookingRequestDTO) {

        Optional<Room> optionalHotelRoom = hotelRoomRepository.
                findById(bookingRequestDTO.getRoomId());

        Optional<Customer> optionalCustomer = customerRepository.
                findById(bookingRequestDTO.getCustomerId());

        if(optionalHotelRoom.isPresent() && optionalCustomer.isPresent()){

            //Checking for the availability between dates for the same room
            List<Booking> validatingBookingList = bookingRepository.findAll()
                    .stream()
                    .filter(n -> n.getRoom().getRoomId() == bookingRequestDTO.getRoomId())
                    .filter(n -> checkDateAvailability(n.getCheckInDate(),n.getCheckOutDate(),
                            bookingRequestDTO.getCheckInDate()))
                    .toList();


            if(validatingBookingList.size()>0){
                //Date can not be booked as the date is booked
                return null;

            }else{
                Room dbExistedRoom = optionalHotelRoom.get();
                Customer dbExistedCustomer = optionalCustomer.get();

                Booking booking = new Booking();

                booking.setRoom(dbExistedRoom);
                booking.setCustomer(dbExistedCustomer);
                booking.setCreatedDate(LocalDate.now());
                booking.setCheckInDate(LocalDate.parse(bookingRequestDTO.getCheckInDate()));
                booking.setCheckOutDate(LocalDate.parse(bookingRequestDTO.getCheckOutDate()));

                return CompletableFuture.completedFuture(bookingRepository.save(booking));
            }

        }else{
            throw new NoRecordExistedException("No existing booking records!");
        }

    }

    /*
    return true:
    check if the user input check-in date is same as a booked check-in date
    check if the user input check-in date is same as a booked check-out date
    check if the user input check-in date between booked check-in and check-out date
    */

    public boolean checkDateAvailability(LocalDate date1, LocalDate date2, String checkDate){

        boolean val =
        LocalDate.parse(checkDate).isEqual(date1)
                | LocalDate.parse(checkDate).isEqual(date2)
                | (LocalDate.parse(checkDate).isAfter(date1) &&
                LocalDate.parse(checkDate).isBefore(date2));

        //if this is true - date is overlapped with check-in date and check-out date

        return val;
    }

    @Override
    public List<Booking> getBookingsByHotel(int hotelId) {

       return bookingRepository.findAll().
                stream()
                .filter(n -> n.getRoom().getHotel().getHotelId() == hotelId)
                .toList();
    }

    @Override
    public Booking updateBooking(BookingRequestDTO bookingRequestDTO,int id) {

        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if(optionalBooking.isPresent()){
            Booking dbExistedBooking = optionalBooking.get();

            dbExistedBooking.setCheckInDate(LocalDate.parse(bookingRequestDTO.getCheckInDate()));
            dbExistedBooking.setCheckOutDate(LocalDate.parse(bookingRequestDTO.getCheckOutDate()));

            return bookingRepository.save(dbExistedBooking);

        }else{
            return null;
        }

    }

    @Override
    public Map<String, String> deleteBooking(int id) {

        Map<String, String> map = new HashMap<>();

        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if(optionalBooking.isPresent()){
            Booking dbExistedBooking = optionalBooking.get();
            bookingRepository.delete(dbExistedBooking);

            map.put("status", "Success");
            map.put("message","Successfully deleted the Booking!");

        }else{

            map.put("status", "Error");
            map.put("message","There is no Bookings for the given booking Id!");

        }
        return map;
    }
}
