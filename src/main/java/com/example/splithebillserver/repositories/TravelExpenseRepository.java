package com.example.splithebillserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.splithebillserver.models.TravelExpense;

public interface TravelExpenseRepository extends CrudRepository<TravelExpense, Integer> {

}
