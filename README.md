# hotel_booking_system
Sample project for Accura-tech

### Rest API endpoints

#### Hotel records management

| Method   | URL                                      | Description                                       |
| -------- | ---------------------------------------- | ----------------------------------------          |
| `GET`    | `/hotel`                                 | Retrieve all Hotel records.                       |
| `POST`   | `/hotel`                                 | Add a new hotel.                                  |
| `DELETE` | `/hotel/{Id}`                            | Delete hotel details for the sent hotel ID.       |
| `PUT`    | `/hotel/{id}`                            | Update hotel details for the sent hotel ID        |

#### Customer records management

| Method   | URL                                      | Description                                                |
| -------- | ---------------------------------------- | ----------------------------------------                   |
| `GET`    | `/customer`                                 | Retrieve all customer records.                          |
| `POST`   | `/customer`                                 | Add a new customer.                                     |
| `DELETE` | `/customer/{Id}`                            | Delete customer details for the sent customer ID.       |
| `PUT`    | `/customer/{id}`                            | Update customer details for the sent customer ID        |

#### Hotel review management

| Method   | URL                                             | Description                                             |
| -------- | ----------------------------------------        | ----------------------------------------                |
| `GET`    | `/hotel_review/search_by_hotel/{Id}`            | Retrieve all Hotel reviews per the sent Hotel ID        | 
| `POST`   | `/hotel_review`                                 | Add a new Hotel review.                                 |
| `DELETE` | `/hotel_review/{Id}`                            | Delete Hotel review for the sent hotel review ID.       |
| `PUT`    | `/hotel_review/{id}`                            | Update Hotel review for the sent hotel review ID        |

#### Hotel room management

| Method   | URL                                             | Description                                         |
| -------- | ----------------------------------------        | ----------------------------------------            |
| `GET`    | `/hotel_room/search_by_hotel/{Id}`              | Retrieve all Hotel rooms per the sent hotel ID      |
| `POST`   | `/hotel_room`                                   | Add a new Hotel room.                               |
| `DELETE` | `/hotel_room/{Id}`                              | Delete Hotel room for the sent hotel room ID.       |
| `PUT`    | `/hotel_room/{id}`                              | Update Hotel room for the sent hotel room ID        |

#### Booking management

| Method   | URL                                             | Description                                      |
| -------- | ----------------------------------------        | ----------------------------------------         |
| `GET`    | `/booking/search_by_hotel/{Id}`                 | Retrieve all bookings per the sent hotel ID      |
| `POST`   | `/booking`                                      | Add a new booking.                               |
| `DELETE` | `/booking/{Id}`                                 | Delete booking for the sent booking ID.          |
| `PUT`    | `/booking/{id}`                                 | Update booking for the sent booking ID           |

#### Search filter 1 : Search hotels as per the sent location, check-in date and checj-out date

| Method   | URL                                             | Description                                      |
| -------- | ----------------------------------------        | ----------------------------------------         |
| `POST`    | `/search_hotels_filter1`                       | Retrieve all hotels per the sent filter params   |

#### Search filter 2 : Search hotels as per the sent location and review

| Method   | URL                                             | Description                                      |
| -------- | ----------------------------------------        | ----------------------------------------         |
| `POST`    | `/search_hotels_filter2`                       | Retrieve all hotels per the sent filter params   |
