package com.br.ent.restspringbootudemy.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ent.restspringbootudemy.repository.UserRepository;
import com.br.ent.restspringbootudemy.security.AccountCredentialsDTO;
import com.br.ent.restspringbootudemy.security.jwt.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="AuthenticationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserRepository repository;
	
	@ApiOperation(value = "Authenticate a user by credentials")
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			var user = repository.findByUsername(username);
			
			var token = "";
			
			if (user != null) {
				token = jwtTokenProvider.createToken(username, user.getRoles());
			}else {
				throw new UsernameNotFoundException("Username "+ username+" not found!");
			}
			
			Map<Object, Object> model = new HashMap<>();
			
			model.put("username", username);
			model.put("password", token);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}
}
