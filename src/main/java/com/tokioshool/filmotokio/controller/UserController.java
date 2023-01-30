package com.tokioshool.filmotokio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokioshool.filmotokio.security.JwtRequest;
import com.tokioshool.filmotokio.security.JwtResponse;
import com.tokioshool.filmotokio.security.JwtTokenUtil;

@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(value = "/login", consumes = "application/json", produces="application/json")
	public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) throws Exception{
		logger.info("Inicio sesion");
		authenticate(authRequest.getUsername(), authRequest.getPassword());
		final String token = jwtTokenUtil.generateToken(authRequest.getUsername());
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			throw new Exception("User disabled", e);
		}catch(BadCredentialsException bce) {
			throw new Exception("Invalid credentials", bce);
		}
	}
}
