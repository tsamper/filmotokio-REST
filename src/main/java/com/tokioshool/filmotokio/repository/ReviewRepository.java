package com.tokioshool.filmotokio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tokioshool.filmotokio.domain.Film;
import com.tokioshool.filmotokio.domain.Review;
import com.tokioshool.filmotokio.domain.User;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
 List<Review> findAll();
 List<Review> findByReUser(User user);
 List<Review> findByReFilm(Film film);
}
