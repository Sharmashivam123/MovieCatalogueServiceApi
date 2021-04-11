package com.moviecatalogue.moviecatalogueservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.moviecatalogue.moviecatalogueservice.models.CatalogueItem;
import com.moviecatalogue.moviecatalogueservice.models.Movie;
import com.moviecatalogue.moviecatalogueservice.models.UserRatings;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
		
	@RequestMapping("/{userId}")
	public List<CatalogueItem> getCataloguesForUser(@PathVariable("userId") String userId){
				
		UserRatings userRatings = restTemplate.getForObject("http://rating-service/ratings/users/"+userId, UserRatings.class);
		
		return userRatings.getRatings().stream().map((rating)->{
			
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class );

			return new CatalogueItem(movie.getName(), "Movie was awesome", rating.getRating());
		})
		.collect(Collectors.toList());
	}


//	@Autowired
//	private WebClient.Builder builder ; 

//	Movie movie = builder.build()
//	.get()
//	.uri("http://localhost:8082/movies/"+rating.getMovieId())
//	.retrieve()
//	.bodyToMono(Movie.class)
//	.block();

//	return Collections.singletonList(new CatalogueItem("Fan", "Movie was awesome", 4));

	
}
