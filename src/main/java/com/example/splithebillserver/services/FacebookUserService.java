package com.example.splithebillserver.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.splithebillserver.models.FacebookUser;
import com.example.splithebillserver.repositories.FacebookUserRepository;

@RestController
@CrossOrigin(origins = "*")
public class FacebookUserService {
	
	@Autowired
	FacebookUserRepository fbRepo;
	
	@PostMapping("/api/fbuser")
	public FacebookUser createUser(@RequestBody FacebookUser newUser) {
		return fbRepo.save(newUser);
	}
	
	@GetMapping("/api/fbuser/{id}")
	public Optional<FacebookUser> findFBUserById(@PathVariable("id") Long id) {
		return fbRepo.findById(id);
	}
}
