package com.tokioshool.filmotokio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokioshool.filmotokio.domain.Film;
import com.tokioshool.filmotokio.repository.FilmRepository;
import com.tokioshool.filmotokio.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	private FilmRepository filmRepository;

	@Override
	public Film findById(long id) {
		return filmRepository.findById(id);
	}
}