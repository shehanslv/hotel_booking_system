package com.shehan.hotel_booking_system.repository;

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
class RoomRepositoryTest {

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelRoomRepository roomRepository;

    @Test
    public void testSaveRoom() {
        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        Room savedRoom = roomRepository.save(room);

        assertNotNull(room);
        assertEquals("Deluxe", savedRoom.getRoomType());
    }

    @Test
    public void testGetRoom() {
        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        Room savedRoom = roomRepository.save(room);

        Room fetchedRoom = roomRepository.
                findById(savedRoom.getRoomId()).orElse(null);

        assertNotNull(fetchedRoom);
        assertEquals(fetchedRoom.getRoomId(), fetchedRoom.getRoomId());
    }

    @Test
    public void testGetRoomList() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        roomRepository.save(new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12));

        roomRepository.save(new Room(savedHotel,"Normal","double bed",
                2400, LocalDate.parse("2024-01-12"),3));

        List<Room> roomList = roomRepository.findAll();
        roomList.stream().filter(n -> n.getHotel().getHotelId() == savedHotel.getHotelId())
                        .toList();

        assertNotNull(roomList);
        assertEquals(2, roomList.size());
    }

    @Test
    public void testDeleteRoom() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        roomRepository.save(room);

        roomRepository.deleteById(room.getRoomId());

        Room deletedRoom =
                roomRepository.findById(room.getRoomId()).orElse(null);

        assertNull(deletedRoom);

    }

    @Test
    public void testUpdateRoom() {

        Hotel hotel = new Hotel("Cinnamon",
                "No.12, Colombo", "Colombo");
        Hotel savedHotel = hotelRepository.save(hotel);

        Room room = new Room(savedHotel,"Deluxe","private pool",
                2400, LocalDate.parse("2024-01-12"),12);

        Room savedRoom = roomRepository.save(room);
        savedRoom.setRoomType("Triple room");

        roomRepository.save(savedRoom);

        Room updatedRoom = roomRepository.findById(savedRoom.getRoomId()).orElse(null);

        assertNotNull(updatedRoom);
        assertEquals("Triple room", updatedRoom.getRoomType());
    }
}