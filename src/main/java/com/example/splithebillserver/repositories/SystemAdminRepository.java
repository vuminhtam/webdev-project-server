package com.example.splithebillserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.splithebillserver.models.SystemAdmin;

public interface SystemAdminRepository extends CrudRepository<SystemAdmin, Integer>{

	@Query("SELECT u FROM SystemAdmin u WHERE u.username=:username AND u.password=:password")
	Optional<SystemAdmin> findUserByCredentials(@Param("username") String username, @Param("password") String password);
}
