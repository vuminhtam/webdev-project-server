package com.example.splithebillserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.splithebillserver.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.username=:username")
	Optional<User> findUserByUsername(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	Optional<User> findUserByCredentials(@Param("username") String username, @Param("password") String password);
	
	@Query(value="INSERT INTO User (id, username) VALUES (:id, :username)", nativeQuery = true)
	User saveFBUser(@Param("id") long id, @Param("username") String username);
}
