package com.example.splithebillserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.splithebillserver.models.SystemAdmin;
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
}
