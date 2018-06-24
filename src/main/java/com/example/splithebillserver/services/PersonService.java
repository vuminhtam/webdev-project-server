package com.example.splithebillserver.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.splithebillserver.models.BillGroup;
import com.example.splithebillserver.models.User;
import com.example.splithebillserver.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class PersonService {

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/api/user/{userId}")
	public User getUserById(@PathVariable("userId") long userId) {
		System.out.print(userId);
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/api/user")
	public User registerUser(@RequestBody User newUser) throws Exception {
		if (userRepo.findUserByUsername(newUser.getUsername()).isPresent()) {
			throw new Exception("Username taken");
		} else {
		return userRepo.save(newUser);
		}
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") long userId) {
		userRepo.deleteById(userId);
	}
	
	@PutMapping("/api/user/{userId}")
	public void updateUser(@PathVariable("userId") long userId, @RequestBody User newUser) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			user.setPhone(newUser.getPhone());
			user.setPictureURL(newUser.getPictureURL());
			user.setUsername(newUser.getUsername());
		}
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user) throws Exception {
		if (userRepo.findUserByCredentials(user.getUsername(), user.getPassword()).isPresent()) {
			return user;
		} else {
			throw new Exception("Account does not exist");
		}
	}
	
	@GetMapping("/api/username/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {
		Optional<User> data = userRepo.findUserByUsername(username);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
}
