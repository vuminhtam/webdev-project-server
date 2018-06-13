package services;

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

import models.Group;
import repositories.GroupAdminRepository;
import repositories.GroupMemberRepository;
import repositories.GroupRepository;

@RestController
@CrossOrigin(origins = "*")
public class GroupService {

	@Autowired
	GroupRepository groupRepo;
	
	@Autowired
	GroupAdminRepository groupAdminRepo;
	
	@Autowired
	GroupMemberRepository groupMemberRepo;
	
	@GetMapping("/api/group/{groupId}")
	public Group getGroupById(@PathVariable ("groupId") int groupId) {
		Optional<Group> data = groupRepo.findById(groupId);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		} 
	}
	
	@PostMapping("/api/group")
	public Group addGroup(@RequestBody Group group) {
		return groupRepo.save(group);
	}
	
	@DeleteMapping("/api/group/{groupId}")
	public void deleteGroup(@PathVariable("groupId") int groupId) {
		groupRepo.deleteById(groupId);
	}
	
	@PutMapping("/api/group/{groupId}")
	public void updateGroup(@PathVariable("groupId") int groupId, @RequestBody Group newGroup) {
		Optional<Group> data = groupRepo.findById(groupId);
		if(data.isPresent()) {
			Group group = data.get();
			group.setName(newGroup.getName());
			group.setNote(newGroup.getNote());
			group.setAdmin(newGroup.getAdmin());
			group.setMembers(newGroup.getMembers());
		}
	}
	
}
