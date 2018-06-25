package com.example.splithebillserver.services;

import java.util.List;
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

import com.example.splithebillserver.models.FacebookUser;
import com.example.splithebillserver.models.User;
import com.example.splithebillserver.repositories.FacebookUserRepository;
import com.example.splithebillserver.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class PersonService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FacebookUserRepository fbRepo;
	
	@GetMapping("/api/user/{userId}")
	public User getUserById(@PathVariable("userId") Long userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			Optional<FacebookUser> data2 = fbRepo.findById(userId);
			if(data2.isPresent()) {
				return data2.get().getUserId();
			} else {
			return null;
			}
		}
	}
	
	@PostMapping("/api/user/{fbId}")
	public User registerUser(@RequestBody User newUser, @PathVariable("fbId") Long facebookId) throws Exception {
		if (userRepo.findUserByUsername(newUser.getUsername()).isPresent()) {
			throw new Exception("Username taken");
		} else {
			User newMember = userRepo.save(newUser);
			System.out.println("fbid:"+facebookId);
			if(facebookId == 0) {
				return newMember;
			} else {
				FacebookUser fb = new FacebookUser(facebookId, newMember);
				fbRepo.save(fb);
				return newMember;
			}
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
			userRepo.save(user);
		}
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user) throws Exception {
		if (userRepo.findUserByCredentials(user.getUsername(), user.getPassword()).isPresent()) {
			return userRepo.findUserByCredentials(user.getUsername(), user.getPassword()).get();
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
	
	
	
	@GetMapping("/api/user")
	public List<User> getAllRegisteredUsers() {
		return (List<User>) userRepo.findAll();
	}
}
