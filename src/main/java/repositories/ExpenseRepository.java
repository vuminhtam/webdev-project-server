package repositories;

import org.springframework.data.repository.CrudRepository;

import models.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

}
