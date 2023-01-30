package com.tokioshool.filmotokio.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokioshool.filmotokio.domain.Film;
import com.tokioshool.filmotokio.domain.Review;
import com.tokioshool.filmotokio.domain.User;
import com.tokioshool.filmotokio.repository.ReviewRepository;
import com.tokioshool.filmotokio.service.ReviewService;
import com.tokioshool.filmotokio.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private UserService userService;
	
	@Override
	public List<Review> findAll() {
		List<Review> reviews = new ArrayList<Review>();
		reviewRepository.findAll().forEach(reviews::add);
		return reviews;
	}

	@Override
	public List<Review> findByUser_review(User user) {
		return reviewRepository.findByReUser(user);
	}

	@Override
	public void add(Review review) {
		review.setDate(LocalDate.now());
		review.setReUser(userService.findByActive(true));
		reviewRepository.save(review);
		
	}

	@Override
	public List<Review> findByFilm_review(Film film) {
		return reviewRepository.findByReFilm(film);
	}

}
