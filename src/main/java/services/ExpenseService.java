package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import models.Expense;
import models.Group;
import models.Person;
import models.TravelExpense;
import models.User;
import repositories.AccomodationExpenseRepository;
import repositories.ExpenseRepository;
import repositories.FoodExpenseRepository;
import repositories.GroupRepository;
import repositories.OtherExpenseRepository;
import repositories.PersonRepository;
import repositories.TravelExpenseRepository;
import repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseService {
	@Autowired 
	GroupRepository groupRepo;
	@Autowired 
	UserRepository userRepo;
	
	@Autowired 
	ExpenseRepository expenseRepo;
	@Autowired  
	  TravelExpenseRepository travelExpRepo; 
	  @Autowired  
	  FoodExpenseRepository foodExpRepo; 
	  @Autowired  
	  AccomodationExpenseRepository accomodationExpRepo; 
	  @Autowired  
	  OtherExpenseRepository otherExpRepo; 
	
	@GetMapping("/api/group/{groupId}/expense")
	public List<Expense> getAllExpenseByGroup(@PathVariable("groupId") int groupId) {
		Optional<Group> optionalExam = groupRepo.findById(groupId);
		if(optionalExam.isPresent()) {
			Group group = optionalExam.get();
			List<Expense> expenses = group.getExpenses();
			return expenses;
		}
		else {
			throw new IllegalArgumentException("Cannot find group " + groupId);
		}
	}
	
	@DeleteMapping("/api/expense/{id}")
	public void deleteExpenseByID(@PathVariable("id") int id) {
		expenseRepo.deleteById(id);
	}
	
	@GetMapping("/api/expense/{id}")
	public Expense getExpenseById(@PathVariable("id") int id) {
		Optional<Expense> optional = expenseRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new IllegalArgumentException("Cannot find expense " + id);
		}
	}
	
	@PostMapping("/api/user/{uid}/group/{groupId}/expense")
	public Expense addExpenseToGroup(
			@PathVariable("uid") int uid,
			@PathVariable("groupId") int groupId,
			@RequestBody Expense newExpense) {
		if(newExpense.getType() == null || newExpense.getType().equals("")) {
			throw new IllegalArgumentException("Null type");
		}
		Optional<User> user = userRepo.findById(uid);
		Optional<Group> group = groupRepo.findById(groupId);
		if(group.isPresent() && user.isPresent()) {
			newExpense.setGroup(group.get());
			newExpense.setExpenser(user.get());
			return expenseRepo.save(newExpense);
		}
		else {
			throw new IllegalArgumentException("Cannot find group " + groupId + " or user " + uid);
		}
	}
	

	@PostMapping("/api/group/{groupId}/expense/save")
	public void saveAllExpenseToGroup(
			@PathVariable("groupId") int groupId,
			@RequestBody List<Expense> newList) {
		List<Expense> groupExpenses = this.getAllExpenseByGroup(groupId);
		for(Expense w: groupExpenses) {
			expenseRepo.deleteById(w.getId());
		}
		expenseRepo.saveAll(newList);
	}	
}
