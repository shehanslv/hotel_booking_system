package com.shehan.hotel_booking_system.repository;

import com.shehan.hotel_booking_system.entity.Booking;
import com.shehan.hotel_booking_system.entity.Customer;
import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.Room;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookingRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelRoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testSaveBooking() {
        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        Room savedRoom = roomRepository.save(room);

        Customer customer = new Customer("Shehan",
                "No.12, Colombo", "123455");
        Customer savedCustomer = customerRepository.save(customer);

        Booking savedBooking = bookingRepository.save(new Booking(room,customer,LocalDate.now(),
                LocalDate.parse("2024-04-01"),LocalDate.parse("2024-04-02")));

        assertNotNull(savedBooking);
        assertEquals(customer, savedBooking.getCustomer());
    }

    @Test
    public void testGetBookingList() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        Room savedRoom = roomRepository.save(room);

        Customer customer = new Customer("Shehan",
                "No.12, Colombo", "123455");
        Customer savedCustomer = customerRepository.save(customer);

        bookingRepository.save(new Booking(room,customer,LocalDate.now(),
                LocalDate.parse("2024-04-01"),LocalDate.parse("2024-04-02")));

        bookingRepository.save(new Booking(room,customer,LocalDate.now(),
                LocalDate.parse("2024-04-04"),LocalDate.parse("2024-04-05")));

        List<Booking> bookingList = bookingRepository.findAll();
        bookingList.stream().filter(n -> n.getRoom().getRoomId() == savedRoom.getRoomId())
                        .toList();

        assertNotNull(bookingList);
        assertEquals(2, bookingList.size());
    }

    @Test
    public void testDeleteBooking() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        Room savedRoom = roomRepository.save(room);

        Customer customer = new Customer("Shehan",
                "No.12, Colombo", "123455");
        Customer savedCustomer = customerRepository.save(customer);

        Booking savedBooking = bookingRepository.save(
                new Booking(room,customer,LocalDate.now(),
                LocalDate.parse("2024-04-01"),LocalDate.parse("2024-04-02")));

        bookingRepository.deleteById(savedBooking.getBookingId());

        Booking deletedBooking =
                bookingRepository.findById(savedBooking.getBookingId()).orElse(null);

        assertNull(deletedBooking);

    }

    @Test
    public void testUpdateBooking() {


        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        Room savedRoom = roomRepository.save(room);

        Customer customer = new Customer("Shehan",
                "No.12, Colombo", "123455");
        Customer savedCustomer = customerRepository.save(customer);

        Booking savedBooking = bookingRepository.save(
                new Booking(room,customer,LocalDate.now(),
                        LocalDate.parse("2024-04-01"),LocalDate.parse("2024-04-02")));

        savedBooking.setCheckOutDate(LocalDate.parse("2024-04-12"));

        bookingRepository.save(savedBooking);

        Booking updatedBooking = bookingRepository.
                findById(savedRoom.getRoomId()).orElse(null);

        assertNotNull(updatedBooking);
        assertEquals(LocalDate.parse("2024-04-12"), updatedBooking.getCheckOutDate());
    }
}