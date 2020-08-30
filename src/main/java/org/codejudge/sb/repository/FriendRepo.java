package org.codejudge.sb.repository;

import java.util.List;

import org.codejudge.sb.entity.Friends;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepo extends CrudRepository<Friends,Long>{
	@Query(value = "select * from codejudge.friends where friend='1'", nativeQuery = true)
	List<Friends> getFriendSuggestion(String friendsufggestionfor);
	
	

}
