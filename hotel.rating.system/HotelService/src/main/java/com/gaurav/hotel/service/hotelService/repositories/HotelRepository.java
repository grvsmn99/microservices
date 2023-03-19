package com.gaurav.hotel.service.hotelService.repositories;

import com.gaurav.hotel.service.hotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
