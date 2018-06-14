package com.example.splithebillserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.splithebillserver.models.FoodExpense;

public interface FoodExpenseRepository extends CrudRepository<FoodExpense, Integer> {

}
