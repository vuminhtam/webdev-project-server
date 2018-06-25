package com.example.splithebillserver.services;

import java.util.ArrayList;
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
import com.example.splithebillserver.models.FacebookUser;
import com.example.splithebillserver.models.BillGroup;
import com.example.splithebillserver.models.PaymentDue;
import com.example.splithebillserver.models.User;
import com.example.splithebillserver.repositories.FacebookUserRepository;
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
	@Autowired
	FacebookUserRepository fbRepo;
	
	@GetMapping("/api/group")
	public List<BillGroup> getAllGroups() {
		return (List<BillGroup>) groupRepo.findAll();
	}
	
	@GetMapping("/api/user/{userId}/group")
	public List<BillGroup> getGroupForUser(@PathVariable ("userId") long userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			System.out.print(user.getUsername());
			List<BillGroup> group1 = user.getGroupsAsAdmin();
			group1.addAll(user.getGroupsAsMember());
			return group1;
		}
		else {
			return null;
		} 
	}
	
	@GetMapping("/api/user/{userId}/admin/group")
	public List<BillGroup> getGroupForUserAdmin(@PathVariable ("userId") long userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			System.out.print(data.get().getUsername());

			return data.get().getGroupsAsAdmin();
		}
		else {
			Optional<FacebookUser> data2 = fbRepo.findById(userId);
			if(data2.isPresent()) {
				System.out.print(data2.get().getUserId().getUsername());

				return data2.get().getUserId().getGroupsAsAdmin();
			} else {
			return null;
			}
		}
	}
	
	@GetMapping("/api/user/{userId}/member/group")
	public List<BillGroup> getGroupForUserMember(@PathVariable ("userId") long userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			return data.get().getGroupsAsMember();
		}
		else {
			Optional<FacebookUser> data2 = fbRepo.findById(userId);
			if(data2.isPresent()) {
				return data2.get().getUserId().getGroupsAsMember();
			} else {
			return null;
			}
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
	
	private User getUserById(Long userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			Optional<FacebookUser> data2 = fbRepo.findById(userId);
			if(data2.isPresent()) {
				return data2.get().getUserId();
			} else {
			return null;
			}
		}
	}
	
	@PostMapping("/api/user/{userId}/group")
	public BillGroup addGroupByUserId(@RequestBody BillGroup group, @PathVariable ("userId") long userId ) {
		User data = this.getUserById(userId);
		if(data != null) {
//			User user = data.get();
			group.setAdmin(data);
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
	
	@PutMapping("/api/group/{groupId}/members")
	public List<User> updateGroupMembers(
			@PathVariable("groupId") int groupId, 
			@RequestBody List<User> newGroupMembers) {
		Optional<BillGroup> data = groupRepo.findById(groupId);
		List<User> newList = new ArrayList<User>();
		for(User u: newGroupMembers) {
			newList.add(this.getUserById(u.getId()));
		}
		
		
		if(data.isPresent()) {
			BillGroup group = data.get();
//			group.setMembers(new ArrayList<User>());
//			groupRepo.save(group);
//			for(int i = 0; i < newList.size(); i++) {
//				newList.get(i).getGroupsAsMember().remove(group);
//				this.addMemberToGroup(groupId, newList.get(i).getId());
//			}
//			groupRepo.save(group);
			
			
			//remove group from the users not in the list
			for(User wasInGroup: group.getMembers()) {
				if(!newList.contains(wasInGroup)) {
					wasInGroup.getGroupsAsMember().remove(group);
					userRepo.save(wasInGroup);
				}
			}
			
			
			group.setMembers(newList);
			groupRepo.save(group);
			return group.getMembers();
		} else {
			return null;
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
	
	@PostMapping("/api/group/{groupId}/newMember/{memberId}")
	public List<User> addMemberToGroup(
			@PathVariable("groupId") int groupId,
			@PathVariable("memberId") long memberId) {
		Optional<BillGroup> data = groupRepo.findById(groupId);
		Optional<User> memData = userRepo.findById(memberId);

		if(data.isPresent() && memData.isPresent()) {
			BillGroup group = data.get();
			User newMem = memData.get();
			if(group.getMembers().contains(newMem)) {
				System.out.println(newMem.getUsername() + " is in group");
				throw new IllegalArgumentException("User already in group!");
			} 

			group.getMembers().add(newMem);
			newMem.getGroupsAsMember().add(group);
			groupRepo.save(group);
			userRepo.save(newMem);
			return group.getMembers();
		} else {
			throw new IllegalArgumentException("Could not find group " + groupId + " or member " + memberId);
		}
	}
	
//	@PostMapping("/api/group/{groupId}/newMember/{}")
//	public BillGroup addMemberToGroup(
//			@PathVariable("groupId") int groupId,
//			@RequestBody User newMemm) {
//		System.out.println("Helloooo!!");
//		Optional<BillGroup> data = groupRepo.findById(groupId);
//		Optional<User> memData = userRepo.findById(newMemm.getId());
//
//		if(data.isPresent() && memData.isPresent()) {
//			BillGroup group = data.get();
//			User newMem = memData.get();
//			group.getMembers().add(newMem);
//			newMem.getGroupsAsMember().add(group);
//			return group;
//		} else {
//			throw new IllegalArgumentException("Could not find group " + groupId);
//		}
//	}
	
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
