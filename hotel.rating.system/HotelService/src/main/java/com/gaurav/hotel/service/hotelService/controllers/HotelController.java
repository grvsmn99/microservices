package com.gaurav.hotel.service.hotelService.controllers;

import com.gaurav.hotel.service.hotelService.entities.Hotel;
import com.gaurav.hotel.service.hotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") String hotelId){
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

}
