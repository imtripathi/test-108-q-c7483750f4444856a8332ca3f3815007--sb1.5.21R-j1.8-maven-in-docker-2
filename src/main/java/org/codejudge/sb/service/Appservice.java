package org.codejudge.sb.service;

import org.codejude.sb.model.FriendList;
import org.codejude.sb.model.FriendSuggestionList;
import org.codejude.sb.model.PendingFriendRequest;
import org.codejude.sb.model.ResponseCode;
import org.codejude.sb.model.UserResponse;
import org.codejudge.sb.exception.BadDataException;
import org.codejudge.sb.exception.FriendReqException;
import org.codejudge.sb.exception.NoDataFoundException;
import org.codejudge.sb.exception.UserCreationException;

public interface Appservice {

	UserResponse create(String username) throws UserCreationException;

	ResponseCode sendFriendRequest(String friendrequestfrom, String friendrequestto) throws FriendReqException, NoDataFoundException, BadDataException;

	PendingFriendRequest getPendingFriendRequest(String pendingfriendreqfor) throws NoDataFoundException, BadDataException;

	FriendList getFriendList(String friendlistfor) throws NoDataFoundException, BadDataException;

	FriendSuggestionList getFriendSuggestionList(String friendsugestionfor) throws NoDataFoundException;

}
