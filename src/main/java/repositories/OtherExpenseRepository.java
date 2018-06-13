package repositories;

import org.springframework.data.repository.CrudRepository;

import models.OtherExpense;

public interface OtherExpenseRepository extends CrudRepository<OtherExpense, Integer> {
	
}
