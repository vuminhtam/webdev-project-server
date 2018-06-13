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

import models.Expense;
import models.Group;
import models.PaymentDue;
import repositories.GroupRepository;
import repositories.PaymentDueRepository;

@RestController
@CrossOrigin(origins = "*")
public class PaymentDueService {
	@Autowired 
	PaymentDueRepository paymentRepository;
	@Autowired 
	GroupRepository groupRepo;
	
	@GetMapping("/api/group/{groupId}/due")
	public List<PaymentDue> getAllDueByGroup(@PathVariable("groupId") int groupId) {
		Optional<Group> optionalGroup = groupRepo.findById(groupId);
		if(optionalGroup.isPresent()) {
			Group group = optionalGroup.get();
			List<PaymentDue> paymentDues = group.getPaymentDue();
			return paymentDues;
		}
		else {
			throw new IllegalArgumentException("Cannot find group " + groupId);
		}
	}
	
	@DeleteMapping("/api/due/{id}")
	public void deletePaymentDueByID(@PathVariable("id") int id) {
		paymentRepository.deleteById(id);
	}
	
	@GetMapping("/api/due/{id}")
	public PaymentDue getPaymentDueById(@PathVariable("id") int id) {
		Optional<PaymentDue> optional = paymentRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new IllegalArgumentException("Cannot find payment due " + id);
		}
	}
	
	@PostMapping("/api/group/{groupId}/due/save")
	public void saveAllExpenseToGroup(
			@PathVariable("groupId") int groupId,
			@RequestBody List<PaymentDue> newList) {
		List<PaymentDue> dues = this.getAllDueByGroup(groupId);
		for(PaymentDue w: dues) {
			paymentRepository.deleteById(w.getId());
		}
		paymentRepository.saveAll(newList);
	}	
}
