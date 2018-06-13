package repositories;

import org.springframework.data.repository.CrudRepository;

import models.GroupMember;

public interface GroupMemberRepository extends CrudRepository<GroupMember, Integer>{

}
