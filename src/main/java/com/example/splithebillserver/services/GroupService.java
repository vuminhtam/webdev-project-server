package com.example.splithebillserver.services;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.splithebillserver.models.Expense;
import com.example.splithebillserver.models.BillGroup;
import com.example.splithebillserver.models.PaymentDue;
import com.example.splithebillserver.models.User;
import com.example.splithebillserver.repositories.GroupRepository;
import com.example.splithebillserver.repositories.PersonRepository;
import com.example.splithebillserver.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class GroupService {

	@Autowired
	GroupRepository groupRepo;
//	
//	@Autowired
//	GroupAdminRepository groupAdminRepo;
//	
//	@Autowired
//	GroupMemberRepository groupMemberRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/api/group")
	public List<BillGroup> getAllGroups() {
		return (List<BillGroup>) groupRepo.findAll();
	}
	
	@GetMapping("/api/user/{userId}/group")
	public List<BillGroup> getGroupForUser(@PathVariable ("userId") int userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			List<BillGroup> group1 = user.getGroupsAsAdmin();
			group1.addAll(user.getGroupsAsMember());
			return group1;
		}
		else {
			return null;
		} 
	}
	
	@GetMapping("/api/user/{userId}/admin/group")
	public List<BillGroup> getGroupForUserAdmin(@PathVariable ("userId") int userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			return data.get().getGroupsAsAdmin();
		}
		else {
			return null;
		} 
	}
	
	@GetMapping("/api/user/{userId}/member/group")
	public List<BillGroup> getGroupForUserMember(@PathVariable ("userId") int userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			return data.get().getGroupsAsMember();
		}
		else {
			return null;
		} 
	}
	
	@GetMapping("/api/group/{groupId}")
	public BillGroup getGroupById(@PathVariable ("groupId") int groupId) {
		Optional<BillGroup> data = groupRepo.findById(groupId);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		} 
	}
	
	@PostMapping("/api/user/{userId}/group")
	public BillGroup addGroupByUserId(@RequestBody BillGroup group, @PathVariable ("userId") int userId ) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			group.setAdmin(user);
			return groupRepo.save(group);
		}
		else {
			return null;
		} 
	}
	
	@DeleteMapping("/api/group/{groupId}")
	public void deleteGroup(@PathVariable("groupId") int groupId) {
		groupRepo.deleteById(groupId);
	}
	
	@PutMapping("/api/group/{groupId}")
	public void updateGroup(@PathVariable("groupId") int groupId, @RequestBody BillGroup newGroup) {
		Optional<BillGroup> data = groupRepo.findById(groupId);
		if(data.isPresent()) {
			BillGroup group = data.get();
			group.setName(newGroup.getName());
			group.setNote(newGroup.getNote());
			group.setAdmin(newGroup.getAdmin());
			group.setMembers(newGroup.getMembers());
			group.setExpenses(newGroup.getExpenses());
			group.setPaymentDue(newGroup.getPaymentDue());
		}
	}
	
	@GetMapping("/api/group/{groupId}/members")
	public List<User> getMembers(@PathVariable("groupId") int groupId) {
		Optional<BillGroup> data = groupRepo.findById(groupId);
		if(data.isPresent()) {
			BillGroup group = data.get();
			return group.getMembers();
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/group/{groupId}/expenses")
	public List<Expense> getExpenses(@PathVariable("groupId") int groupId) {
		Optional<BillGroup> data = groupRepo.findById(groupId);
		if(data.isPresent()) {
			BillGroup group = data.get();
			return group.getExpenses();
		} else {
			return null;
		}
	}
	
//	@GetMapping("/api/group/{groupId}/due")
//	public List<PaymentDue> getPaymentDue(@PathVariable("groupId") int groupId) {
//		Optional<Group> data = groupRepo.findById(groupId);
//		if(data.isPresent()) {
//			Group group = data.get();
//			return group.getPaymentDue();
//		} else {
//			return null;
//		}
//	}
	
}
