package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.EssayQuestion;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.Group;
import com.example.webdevsummer12018.models.Question;

import models.Expense;
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
	
	@DeleteMapping("/api/{expenseType}/{id}")
	public void deleteQuestionByID(@PathVariable("id") int id) {
		expenseRepo.deleteById(id);
	}
	
//	@GetMapping("/api/{expenseType}/{eid}")
//	public Expense getExpenseByIdAndType(
//			@PathVariable("expenseType") String expenseType,
//			@PathVariable("eid") int eid) {
//		try {
//			switch(expenseType) {
//			case "travel": return travelExpRepo.findById(eid).get(); break;
//			case "accomodation": return accomodationExpRepo.findById(eid).get(); break;
//			case "food": return foodExpRepo.findById(eid).get(); break;
//			case "other": return otherExpRepo.findById(eid).get(); break;
//			default: throw new IllegalArgumentException("No such expense of type " + expenseType);
//			}
//		} 
//		catch (Exception e) {
//			throw new IllegalArgumentException("Cannot find expense " + eid + " of type " + expenseType);
//		}
//	}
	
//	@PostMapping("/api/group/{groupId}/{expenseType}")
//	public void addExpenseByTypeToGroup(
//			@PathVariable("expenseType") String expenseType,
//			@PathVariable("groupId") int groupId) {
//		Optional<Group> res = groupRepo.findById(groupId);
//		if(res.isPresent()) {
//			Group group = res.get();
//			
//		}
//		else {
//			throw new IllegalArgumentException("Cannot find topic " + id);
//		}
//	}
	
	
	
	
	
	
	
	
}
