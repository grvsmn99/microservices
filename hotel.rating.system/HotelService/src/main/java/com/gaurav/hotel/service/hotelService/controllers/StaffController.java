package com.gaurav.hotel.service.hotelService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public List<String> getAllStaffs(){
        List<String> staffs = new ArrayList<>();
        staffs.add("Shyam");
        staffs.add("Kishan");
        staffs.add("Vinod");
        return staffs;
    }
}
