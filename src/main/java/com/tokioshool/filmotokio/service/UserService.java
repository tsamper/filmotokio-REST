package com.tokioshool.filmotokio.service;

import com.tokioshool.filmotokio.domain.User;

public interface UserService {
	public User findByUsername(String username);
	public User findByActive(boolean bool);
	public User findById(long id);
}
