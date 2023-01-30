package com.tokioshool.filmotokio.service;

import java.util.List;

import com.tokioshool.filmotokio.domain.Film;
import com.tokioshool.filmotokio.domain.Review;
import com.tokioshool.filmotokio.domain.User;

public interface ReviewService {
 public  List<Review> findAll();
 public  List<Review> findByUser_review(User user);
 public void add(Review review);
 public List<Review> findByFilm_review(Film film);
}
