package com.shehan.hotel_booking_system.repository;

import com.shehan.hotel_booking_system.entity.Hotel;
import com.shehan.hotel_booking_system.entity.HotelReview;
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
class HotelReviewRepositoryTest {

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelReviewRepository hotelReviewRepository;

    @Test
    public void testSaveReview() {
        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        HotelReview hotelReview = new HotelReview(savedHotel,"Nice hotel",LocalDate.now());

        HotelReview savedReview = hotelReviewRepository.save(hotelReview);

        assertNotNull(savedReview);
        assertEquals("Nice hotel", savedReview.getReview());
    }

    @Test
    public void testGetReview() {
        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        HotelReview hotelReview = new HotelReview(savedHotel,"Nice hotel",LocalDate.now());

        HotelReview savedReview = hotelReviewRepository.save(hotelReview);

        HotelReview fetchedReview = hotelReviewRepository.
                findById(savedReview.getHotelReviewId()).orElse(null);

        assertNotNull(fetchedReview);
        assertEquals(savedReview.getHotelReviewId(), fetchedReview.getHotelReviewId());
    }

    @Test
    public void testGetReviewList() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        hotelReviewRepository.save(new HotelReview(savedHotel,
                "Nice hotel",LocalDate.now()));

        hotelReviewRepository.save(new HotelReview(savedHotel,
                "Nice hotel",LocalDate.now()));

        List<HotelReview> hotelReviewList = hotelReviewRepository.findAll();
        hotelReviewList.stream().filter(n -> n.getHotel().getHotelId() == savedHotel.getHotelId())
                        .toList();

        assertNotNull(hotelReviewList);
        assertEquals(2, hotelReviewList.size());
    }

    @Test
    public void testDeleteReview() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        HotelReview hotelReview = hotelReviewRepository.save(new HotelReview(savedHotel,
                "Nice hotel",LocalDate.now()));

        hotelReviewRepository.deleteById(hotelReview.getHotelReviewId());

        HotelReview deletedReview =
                hotelReviewRepository.findById(
                        hotelReview.getHotelReviewId()).orElse(null);

        assertNull(deletedReview);

    }

    @Test
    public void testUpdateReview() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        HotelReview hotelReview = hotelReviewRepository.save(new HotelReview(savedHotel,
                "Nice hotel",LocalDate.now()));

        hotelReview.setReview("Calm environment");

        hotelReviewRepository.save(hotelReview);

        HotelReview updatedReview = hotelReviewRepository.findById(
                hotelReview.getHotelReviewId()).orElse(null);

        assertNotNull(updatedReview);
        assertEquals("Calm environment", updatedReview.getReview());
    }
}