package com.example.splithebillserver.services;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.example.splithebillserver.models.Expense;
import com.example.splithebillserver.models.BillGroup;
import com.example.splithebillserver.models.PaymentDue;
import com.example.splithebillserver.models.User;
import com.example.splithebillserver.repositories.GroupRepository;
import com.example.splithebillserver.repositories.PaymentDueRepository;

@RestController
@CrossOrigin(origins = "*")
public class PaymentDueService {
	@Autowired 
	PaymentDueRepository paymentRepository;
	@Autowired 
	GroupRepository groupRepo;
	
	@GetMapping("/api/group/{groupId}/due")
	public List<PaymentDue> getAllDueByGroup(@PathVariable("groupId") int groupId) {
		Optional<BillGroup> optionalGroup = groupRepo.findById(groupId);
		if(optionalGroup.isPresent()) {
			BillGroup group = optionalGroup.get();
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
	
	@PutMapping("/api/group/{groupId}/due/calculate")
	public List<PaymentDue> calculateCurrentDues(
			@PathVariable("groupId") int groupId) {
		Optional<BillGroup> optionalGroup = groupRepo.findById(groupId);
		if(optionalGroup.isPresent()) {
			BillGroup group = optionalGroup.get();
			group.setPaymentDue(this.calculateDues(group));
			groupRepo.save(group);
			return group.getPaymentDue();
		}
		else {
			throw new IllegalArgumentException("Cannot find group " + groupId);
		}
	}
	
	private List<PaymentDue> calculateDues(BillGroup group) {

		List<Expense> expenses = group.getExpenses();
		//add expenses = 0 of users not expensed
		for(User mem: group.getMembers()) {
			if(!this.contains(expenses, mem)) {
				expenses.add(new Expense())
			}
		}
		
		//calculate total expenses
		int totalExpenses = 0;
		for(Expense e: expenses) {
			totalExpenses += e.getAmmount();
		}
		int balance = totalExpenses/group.getMembers().size();

		//list of balances
		UserBalance[] balances = new UserBalance[group.getMembers().size()];
		
		
		for(int i = 0; i < group.getMembers().size(); i++) {
			User member = group.getMembers().get(i);
//			for(Expense e : expenses) {
//				
//			}
			Expense e = expenses.get(i);
			int userBalance = e.getAmmount() - balance;
			balances[i] = new UserBalance(e.getExpenser(), userBalance);
		}
		
		System.out.println("balances " + balances.length + " expense size " + expenses.size());
		
		//sort ascending
		Arrays.sort(balances);
		//partition to 2 groups
		List<UserBalance> receive = new ArrayList<UserBalance>();
		List<UserBalance> pay = new ArrayList<UserBalance>();
		for(UserBalance b: balances) {
			System.out.print("balance for "+ b.getUser().getUsername() + " ammount "+b.getAmmount());
			if(b.getAmmount() >= 0) {
				receive.add(b);
			}
			else {
				pay.add(b);
			}
		}
//		
//		System.out.println("receive list: " + receive.get(0).getUser().getUsername());
//		System.out.println("pay list: " + pay.get(0).getUser().getUsername());

		//payList pays the receiveList
		int i = 0;
		int j = 0;
		List<PaymentDue> dues = new ArrayList<PaymentDue>();
		while(i < receive.size() && j < pay.size()) {
			if(receive.get(i).getAmmount() == 0) {
				continue; //this user does not need any paymentDue
			} else {
				//pay pays the receive 
				int ammount = Math.min(receive.get(i).getAmmount(), receive.get(j).getAmmount());
				//create paymentDue from pay to receive
				dues.add(new PaymentDue(pay.get(j).getUser(), receive.get(j).getUser(), ammount));
				receive.get(i).setAmmount(Math.abs(receive.get(i).getAmmount() - ammount));
				pay.get(j).setAmmount(Math.abs(pay.get(j).getAmmount() - ammount));
			}
			//move on to the next user for i and j
			if(receive.get(i).getAmmount() == 0) {
				i++; //receive enough 
			}
			if(pay.get(j).getAmmount() == 0) {
				j++; //pay enough
			}
		}
		return dues;
	}
	
	private final class UserBalance implements Comparable {
		private User user;
		private int ammount;
		
		UserBalance(User user, int ammount) {
			this.setUser(user);
			this.setAmmount(ammount);
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public int getAmmount() {
			return ammount;
		}

		public void setAmmount(int ammount) {
			this.ammount = ammount;
		}

		@Override
		public int compareTo(Object o) {
			if(o instanceof UserBalance) {
				return this.getAmmount() - ((UserBalance) o).getAmmount();
			} else {
				throw new IllegalArgumentException("Cannot compare user balance to other stuffs");
			}
		}
	}
}
