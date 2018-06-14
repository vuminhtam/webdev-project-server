package com.example.splithebillserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.splithebillserver.models.BillGroup;

public interface GroupRepository extends CrudRepository<BillGroup, Integer>{

}
