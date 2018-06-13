package repositories;

import org.springframework.data.repository.CrudRepository;

import models.FoodExpense;

public interface FoodExpenseRepository extends CrudRepository<FoodExpense, Integer> {

}
