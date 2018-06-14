package com.example.splithebillserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.splithebillserver.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
