package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import repositories.ExpenseRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseService {
	@Autowired 
	ExpenseRepository expenseRepo;
}
