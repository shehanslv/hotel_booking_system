CREATE TABLE IF NOT EXISTS hotel (
  hotel_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  hotel_name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  version INTEGER
);

CREATE TABLE IF NOT EXISTS customer (
  customer_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  customer_name VARCHAR(255) NOT NULL,
  customer_address VARCHAR(255) NOT NULL,
  customer_contact_number VARCHAR(20) NOT NULL,
  version INTEGER
);

CREATE TABLE IF NOT EXISTS room (
  room_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  hotel_id INTEGER NOT NULL,
  room_type VARCHAR(100) NOT NULL,
  special_features VARCHAR(255),
  price DECIMAL(10,2) NOT NULL,
  price_until DATE NOT NULL,
  max_persons INTEGER NOT NULL,
  version INTEGER,
  FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

CREATE TABLE IF NOT EXISTS hotel_review (
  hotel_review_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  hotel_id INTEGER NOT NULL,
  created_date DATE NOT NULL,
  review VARCHAR(255),
  version INTEGER,
  FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

CREATE TABLE IF NOT EXISTS booking (
  booking_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  room_id INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  created_date DATE NOT NULL,
  check_in_date DATE NOT NULL,
  check_out_date DATE NOT NULL,
  version INTEGER,
  FOREIGN KEY (room_id) REFERENCES room(room_id),
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);