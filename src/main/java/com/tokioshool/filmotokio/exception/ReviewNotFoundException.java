package com.tokioshool.filmotokio.exception;

public class ReviewNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReviewNotFoundException() {
		super();
	}
	
	public ReviewNotFoundException(String message) {
		super(message);
	}
	
	public ReviewNotFoundException(long id) {
		super("Review not found:" + id);
	}
}
