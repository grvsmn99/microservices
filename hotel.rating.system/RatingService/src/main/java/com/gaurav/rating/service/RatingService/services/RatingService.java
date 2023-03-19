package com.gaurav.rating.service.RatingService.services;

import com.gaurav.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
