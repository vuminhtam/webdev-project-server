package repositories;

import org.springframework.data.repository.CrudRepository;

import models.TravelExpense;

public interface TravelExpenseRepository extends CrudRepository<TravelExpense, Integer> {

}
