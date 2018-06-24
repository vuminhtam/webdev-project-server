package com.example.splithebillserver.repositories;


import org.springframework.data.repository.CrudRepository;

import com.example.splithebillserver.models.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
	
}
