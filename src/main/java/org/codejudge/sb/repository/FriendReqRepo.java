package org.codejudge.sb.repository;

import java.io.Serializable;
import java.util.List;

import org.codejudge.sb.entity.FriendRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FriendReqRepo extends CrudRepository<FriendRequest,Serializable> {

	 @Query(value = "select * from friend_request where friend_req_from=?1 and accepted='0' ", nativeQuery = true)
	List<FriendRequest> findfriendReq(String name);
	 
	 @Query(value = "select * from codejudge.friend_request where  friend_req_from=?1 and friend_req_to=?2", nativeQuery = true)
	 List<FriendRequest> updateaccepted( String friend_req_from, String friend_req_to );

	 
	 @Query(value = "select * from codejudge.friend_request where friend_req_to=?1 and accepted='0'", nativeQuery = true)
	 List<FriendRequest> getpendingFriendReq( String friend_req_to );
	 
	 @Query(value = "select * from codejudge.friend_request where friend_req_to=?1 and accepted='1'", nativeQuery = true)
	 List<FriendRequest> getFriendList( String friend_req_to );

	 @Query(value = "select * from codejudge.friend_request where accepted='1'", nativeQuery = true)
	List<FriendRequest> getFriendSuggestion(String friend_req_from);

}
