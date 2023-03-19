package com.gaurav.user.service.external.services;

import com.gaurav.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{id}")
    Hotel getHotel(@PathVariable("id") String hotelId);
}
