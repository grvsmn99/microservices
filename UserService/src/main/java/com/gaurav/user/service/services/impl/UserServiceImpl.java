package com.gaurav.user.service.services.impl;

import com.gaurav.user.service.entities.Hotel;
import com.gaurav.user.service.entities.Rating;
import com.gaurav.user.service.entities.User;
import com.gaurav.user.service.exceptions.ResourceNotFoundException;
import com.gaurav.user.service.external.services.HotelService;
import com.gaurav.user.service.external.services.RatingService;
import com.gaurav.user.service.repositories.UserRepository;
import com.gaurav.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User save(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }
    //Using feingClient
    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            List<Rating> ratings = ratingService.fetchRatingsByUserId(user.getUserId());
            ratings.forEach(rating -> {
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
            });
            user.setRatingList(ratings);
        });
        return users;

    }

    @Override
    public User getUser(String userId) {
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user withgiven id is not found on server : "+userId));
         //Fetch Ratings from Rating Service
         Rating[] ratings= restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
         List<Rating> list = Arrays.asList(ratings);
         List<Rating> ratingList = list.stream()
                         .map( rating -> {
                             //Api call to get hotel info
                             ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                             if(hotelEntity.getStatusCode().equals(HttpStatus.OK)) {
                                 //set hotel to rating object
                                 rating.setHotel(hotelEntity.getBody());
                             }
                             return rating;
                         }).collect(Collectors.toList());

         user.setRatingList(list);
         return user;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
