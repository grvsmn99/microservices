package com.gaurav.hotel.service.hotelService.services.impl;

import com.gaurav.hotel.service.hotelService.entities.Hotel;
import com.gaurav.hotel.service.hotelService.exceptions.ResourceNotFoundException;
import com.gaurav.hotel.service.hotelService.repositories.HotelRepository;
import com.gaurav.hotel.service.hotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel saveHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setUuid(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String uuid) {
        return hotelRepository.findById(uuid).orElseThrow( () -> new ResourceNotFoundException("Hotel not found with given id:"+uuid));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
