package com.tokioshool.filmotokio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tokioshool.filmotokio.domain.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {
	Film findById(long id);
}
