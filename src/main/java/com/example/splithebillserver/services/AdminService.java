package com.example.splithebillserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.splithebillserver.models.SystemAdmin;
import com.example.splithebillserver.models.User;
import com.example.splithebillserver.repositories.SystemAdminRepository;

@RestController
@CrossOrigin(origins = "*")
public class AdminService {

	@Autowired
	SystemAdminRepository repo;
	
	@PostMapping("/api/admin")
	public SystemAdmin registerAdmin(@RequestBody SystemAdmin newUser) throws Exception {
		return this.repo.save(newUser);
	}
	
	@GetMapping("/api/admin")
	public List<SystemAdmin> getAllAdmins() {
		return (List<SystemAdmin>) repo.findAll();
	}
	
	@GetMapping("/api/admin/{id}")
	public SystemAdmin findAdminById(@PathVariable("id") int id) {
		Optional<SystemAdmin> data = repo.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/api/admin/login")
	public SystemAdmin login(@RequestBody User user) throws Exception {
		if (repo.findUserByCredentials(user.getUsername(), user.getPassword()).isPresent()) {
			return repo.findUserByCredentials(user.getUsername(), user.getPassword()).get();
		} else {
			throw new Exception("Account does not exist");
		}
	}
}
