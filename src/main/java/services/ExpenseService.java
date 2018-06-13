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

import com.example.webdevsummer12018.models.EssayQuestion;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.Group;
import com.example.webdevsummer12018.models.Question;
import com.example.webdevsummer12018.models.User;

import models.Expense;
import models.TravelExpense;
import repositories.AccomodationExpenseRepository;
import repositories.ExpenseRepository;
import repositories.FoodExpenseRepository;
import repositories.OtherExpenseRepository;
import repositories.TravelExpenseRepository;

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
			newExpense.setGroup(group);
			newExpense.setExpenser(user);
			return expenseRepo.save(newExpense);
		}
		else {
			throw new IllegalArgumentException("Cannot find group " + groupId + " or user " + uid);
		}
	}
	
	
	@PutMapping("/api/travel/{id}")
	public TravelExpense updateTravelExpenseById(
			@PathVariable("id") int id,
			@RequestBody TravelExpense newExpense) {
		Optional<TravelExpense> retrieve = travelExpRepo.findById(id);
		if(retrieve.isPresent()) {
			TravelExpense expense = retrieve.get();
			expense.setAmmount(newExpense.getAmmount());
			expense.setExpenser(newExpense.getExpenser());
			expense.setFrom(newExpense.getFrom());
			expense.setTo(newExpense.getTo());
			expense.setNote(newExpense.getNote());
			return expense;
		}
		else {
			throw new IllegalArgumentException("Cannot find expense " + id);
		}
	}

	
}
