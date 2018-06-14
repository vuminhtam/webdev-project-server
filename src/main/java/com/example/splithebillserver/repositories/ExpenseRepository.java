package com.example.splithebillserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.splithebillserver.models.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

}
