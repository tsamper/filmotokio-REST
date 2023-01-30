package com.tokioshool.filmotokio.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
	private String title;
	private String textReview;
	private LocalDate date;
	private FilmDTO film_review;
}