package com.gaurav.hotel.service.hotelService.services;

import com.gaurav.hotel.service.hotelService.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);
    Hotel getHotel(String uuid);
    List<Hotel> getAllHotels();
}
