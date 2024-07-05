package com.shehan.hotel_booking_system.repository;

import com.shehan.hotel_booking_system.entity.Hotel;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HotelRepositoryTest {

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testSaveHotel() {
        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);
        assertNotNull(savedHotel);
        assertEquals("Cinnamon", savedHotel.getHotelName());
    }

    @Test
    public void testGetHotel() {
        Hotel hotel = hotelRepository.save(new
                Hotel("Cinnamon",
                "No.12, Colombo", "Colombo"));
        Hotel fetchedHotel = hotelRepository.findById(hotel.getHotelId()).orElse(null);
        assertNotNull(fetchedHotel);
        assertEquals(hotel.getHotelId(), fetchedHotel.getHotelId());
    }

    @Test
    public void testGetHotelList() {
        hotelRepository.save(new
                Hotel("Cinnamon",
                "No.12, Colombo", "Colombo"));

        hotelRepository.save(new
                Hotel("Hilton",
                "No.12, Galle", "Galle"));
        List<Hotel> hotelList = hotelRepository.findAll();

        assertNotNull(hotelList);
        assertEquals(2, hotelList.size());
    }

    @Test
    public void testDeleteHotel() {

        Hotel hotel =
                hotelRepository.save(new
                        Hotel("Cinnamon",
                        "No.12, Colombo", "Colombo"));

        hotelRepository.deleteById(hotel.getHotelId());

        Hotel deletedHotel =
                hotelRepository.findById(hotel.getHotelId()).orElse(null);

        assertNull(deletedHotel);

    }

    @Test
    public void testUpdateHotel() {
        Hotel savedhotel =
                hotelRepository.save(new
                        Hotel("Cinnamon",
                        "No.12, Colombo", "Colombo"));

        savedhotel.setHotelName("Galadari");
        hotelRepository.save(savedhotel);

        Hotel updatedHotel = hotelRepository.findById(savedhotel.getHotelId()).orElse(null);

        assertNotNull(updatedHotel);
        assertEquals("Galadari", updatedHotel.getHotelName());
    }
}