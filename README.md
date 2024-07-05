# hotel_booking_system
Sample project for Accura-tech

## Rest API endpoints

#### Hotel records management

| Method   | URL                                      | Description                                       |
| -------- | ---------------------------------------- | ----------------------------------------          |
| `GET`    | `/hotel`                                 | Retrieve all Hotel records.                       |
| `POST`   | `/hotel`                                 | Add a new hotel.                                  |
| `DELETE` | `/hotel/{Id}`                            | Delete hotel details for the sent hotel ID.       |
| `PUT`    | `/hotel/{id}`                            | Update hotel details for the sent hotel ID        |

```
/hotel - POST : Sample request body

{
    "hotelName" : "Taj Samudra",
    "address":"No.8, Kandy",
    "location": "Kandy"
}

```

#### Customer records management

| Method   | URL                                      | Description                                                |
| -------- | ---------------------------------------- | ----------------------------------------                   |
| `GET`    | `/customer`                                 | Retrieve all customer records.                          |
| `POST`   | `/customer`                                 | Add a new customer.                                     |
| `DELETE` | `/customer/{Id}`                            | Delete customer details for the sent customer ID.       |
| `PUT`    | `/customer/{id}`                            | Update customer details for the sent customer ID        |

```
/customer - POST : Sample request body

{
    "customerName" : "Shehan silva",
    "customerAddress":"No.5, Colombo 02",
    "customerContactNumber": "0112230374"
}

```

#### Hotel review management

| Method   | URL                                             | Description                                             |
| -------- | ----------------------------------------        | ----------------------------------------                |
| `GET`    | `/hotel_review/search_by_hotel/{Id}`            | Retrieve all Hotel reviews per the sent Hotel ID        | 
| `POST`   | `/hotel_review`                                 | Add a new Hotel review.                                 |
| `DELETE` | `/hotel_review/{Id}`                            | Delete Hotel review for the sent hotel review ID.       |
| `PUT`    | `/hotel_review/{id}`                            | Update Hotel review for the sent hotel review ID        |

```
/hotel_review - POST : Sample request body

{
    "hotelId" : "2",
    "review":"Nice hotel"
}

```

#### Hotel room management

| Method   | URL                                             | Description                                         |
| -------- | ----------------------------------------        | ----------------------------------------            |
| `GET`    | `/hotel_room/search_by_hotel/{Id}`              | Retrieve all Hotel rooms per the sent hotel ID      |
| `POST`   | `/hotel_room`                                   | Add a new Hotel room.                               |
| `DELETE` | `/hotel_room/{Id}`                              | Delete Hotel room for the sent hotel room ID.       |
| `PUT`    | `/hotel_room/{id}`                              | Update Hotel room for the sent hotel room ID        |

```
/hotel_room - POST : Sample request body

{
    "hotelId": 2,
    "roomType": "Normal",
    "specialFeatures": "Private pool",
    "price": 1000,
    "priceUntilDate": "2024-08-23",
    "maxPersons": 3
}

```

#### Booking management

| Method   | URL                                             | Description                                      |
| -------- | ----------------------------------------        | ----------------------------------------         |
| `GET`    | `/booking/search_by_hotel/{Id}`                 | Retrieve all bookings per the sent hotel ID      |
| `POST`   | `/booking`                                      | Add a new booking.                               |
| `DELETE` | `/booking/{Id}`                                 | Delete booking for the sent booking ID.          |
| `PUT`    | `/booking/{id}`                                 | Update booking for the sent booking ID           |

```
/booking - POST : Sample request body

{
    "roomId": 2,
    "checkInDate": "2024-11-07",
    "checkOutDate": "2024-11-08",
    "customerId": 2
}

```

## Search filters

#### Search filter 1 : Search hotels as per the sent location, check-in date and checj-out date

| Method   | URL                                             | Description                                      |
| -------- | ----------------------------------------        | ----------------------------------------         |
| `POST`    | `/search_hotels_filter1`                       | Retrieve all hotels per the sent filter params   |

```
/search_hotels_filter1 - POST : Sample request body

{
    "location":"colombo",
    "checkInDate":"2013-09-02",
    "checkOutDate":"2013-09-03"
}

```

#### Search filter 2 : Search hotels as per the sent location and review

| Method   | URL                                             | Description                                      |
| -------- | ----------------------------------------        | ----------------------------------------         |
| `POST`    | `/search_hotels_filter2`                       | Retrieve all hotels per the sent filter params   |

```
/search_hotels_filter2 - POST : Sample request body

{
    "review": "nice hotel",
    "location": "colombo"
}

```

