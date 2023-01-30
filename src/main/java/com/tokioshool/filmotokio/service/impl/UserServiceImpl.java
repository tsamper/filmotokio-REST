package com.tokioshool.filmotokio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokioshool.filmotokio.domain.User;
import com.tokioshool.filmotokio.repository.UserRepository;
import com.tokioshool.filmotokio.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByActive(boolean bool) {
		return userRepository.findByActive(bool);
	}
	
	@Override
	public User findById(long id) {
		return userRepository.findById(id);
	}
}
