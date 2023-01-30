package com.tokioshool.filmotokio.controller;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tokioshool.filmotokio.domain.Film;
import com.tokioshool.filmotokio.domain.Review;
import com.tokioshool.filmotokio.domain.User;
import com.tokioshool.filmotokio.dto.ReviewDTO;
import com.tokioshool.filmotokio.exception.ReviewNotFoundException;
import com.tokioshool.filmotokio.service.FilmService;
import com.tokioshool.filmotokio.service.ReviewService;
import com.tokioshool.filmotokio.service.UserService;

@RestController
public class ReviewController {
	
	private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserService userService;
	@Autowired
	private FilmService filmService;
	
	private ReviewDTO convertToDTO(Review review) {
		return modelMapper.map(review, ReviewDTO.class);
	}
	
	private Review convertToDomain(ReviewDTO reviewDTO) {
		return modelMapper.map(reviewDTO, Review.class);
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<List<ReviewDTO>> getReviews(){
		List<Review> reviews = reviewService.findAll();
		List<ReviewDTO> reviewsDTO = new ArrayList<>();
		reviews.stream().forEach(r -> reviewsDTO.add(convertToDTO(r)));
		return new ResponseEntity<List<ReviewDTO>>(reviewsDTO, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> getReviewsForUser(@PathVariable Long id){
		User user = userService.findById(id);
		List<Review> reviews = reviewService.findByUser_review(user);
		List<ReviewDTO> reviewsDTO = new ArrayList<>();
		reviews.stream().forEach(r -> reviewsDTO.add(convertToDTO(r)));
		return new ResponseEntity<List<ReviewDTO>>(reviewsDTO, HttpStatus.OK);  
	}
	
	@GetMapping("/film/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> getReviewsFoFilm(@PathVariable Long id){
		Film film = filmService.findById(id);
		List<Review> reviews = reviewService.findByFilm_review(film);
		List<ReviewDTO> reviewsDTO = new ArrayList<>();
		reviews.stream().forEach(r -> reviewsDTO.add(convertToDTO(r)));
		return new ResponseEntity<List<ReviewDTO>>(reviewsDTO, HttpStatus.OK);  
	}
	
	@RequestMapping("/new-review")
	public void addReview(@RequestBody ReviewDTO reviewDTO, BindingResult Bresult){
		Film film = filmService.findById(reviewDTO.getFilm_review().getId());
		Review review = convertToDomain(reviewDTO);
		review.setReFilm(film);
		reviewService.add(review);
	}
	
	@ExceptionHandler(ReviewNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> handleException(ReviewNotFoundException rnfe){
		Response response = Response.errorResponse(Response.NOT_FOUND, rnfe.getMessage());
		logger.error(rnfe.getMessage(), rnfe);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
