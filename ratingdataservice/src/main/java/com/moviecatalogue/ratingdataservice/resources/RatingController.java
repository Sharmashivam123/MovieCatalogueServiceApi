package com.moviecatalogue.ratingdataservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviecatalogue.ratingdataservice.models.Rating;
import com.moviecatalogue.ratingdataservice.models.UserRatings;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@GetMapping("/users/{userId}")
	public UserRatings getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratings = new ArrayList<>(Arrays.asList(
				new Rating("1234", 3), new Rating("1235", 4)));
		
		return new UserRatings(ratings);
	}
}
