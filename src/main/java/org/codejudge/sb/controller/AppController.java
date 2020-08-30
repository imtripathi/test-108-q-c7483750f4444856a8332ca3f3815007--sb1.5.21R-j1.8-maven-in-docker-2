package org.codejudge.sb.controller;

import org.codejude.sb.model.CreateUser;
import org.codejude.sb.model.FriendList;
import org.codejude.sb.model.FriendSuggestionList;
import org.codejude.sb.model.PendingFriendRequest;
import org.codejude.sb.model.ResponseCode;
import org.codejude.sb.model.UserResponse;
import org.codejudge.sb.exception.FriendReqException;
import org.codejudge.sb.exception.NoDataFoundException;
import org.codejudge.sb.exception.UserCreationException;
import org.codejudge.sb.service.Appservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "User Controller")

@RestController
@RequestMapping
public class AppController {

	@Autowired
	private Appservice appService;
	
    @SuppressWarnings("static-access")
	@ApiOperation("This create the username")
    @PostMapping("/create/{username}")
    public ResponseEntity<UserResponse> create(@RequestBody CreateUser  username ) throws UserCreationException {
    	UserResponse usrRes= new UserResponse();
    	usrRes=appService.create(username.getUsername());
        return ResponseEntity.ok(usrRes).status(201).body(usrRes);
    }
    
    
    @SuppressWarnings("static-access")
	@ApiOperation("This sends the friend request")
    @PostMapping("/add/{friendrequestfrom}/{friendrequestto}")
    public ResponseEntity<ResponseCode> sendFriendRequest(@PathVariable(name ="friendrequestfrom") String friendrequestfrom,@PathVariable(name ="friendrequestto") String friendrequestto ) throws FriendReqException  {
    	ResponseCode res= new ResponseCode();
    	res=appService.sendFriendRequest(friendrequestfrom,friendrequestto);
        return ResponseEntity.ok(res).status(202).body(res);
    }
    
    @SuppressWarnings("static-access")
	@ApiOperation("This gets the pending friend request")
    @GetMapping("/friendRequests/{pendingfriendreqfor}")
    public ResponseEntity<PendingFriendRequest> getPendingFriendRequest(@PathVariable(name ="pendingfriendreqfor") String pendingfriendreqfor ) throws NoDataFoundException   {
    	PendingFriendRequest res= new PendingFriendRequest();
    	res=appService.getPendingFriendRequest(pendingfriendreqfor);
        return ResponseEntity.ok(res).status(200).body(res);
    }

    @SuppressWarnings("static-access")
	@ApiOperation("This gets the friend list of user")
    @GetMapping("/friends/{friendlistfor}")
    public ResponseEntity<FriendList> getFriendList(@PathVariable(name ="friendlistfor") String friendlistfor ) throws NoDataFoundException   {
    	FriendList res= new FriendList();
    	res=appService.getFriendList(friendlistfor);
        return ResponseEntity.ok(res).status(200).body(res);
    }
    
    @SuppressWarnings("static-access")
	@ApiOperation("This gets the friend suggesttion of user")
    @GetMapping("/suggestions/{friendsugestionfor}")
    public ResponseEntity<FriendSuggestionList> getFriendSuggestionList(@PathVariable(name ="friendsugestionfor") String friendsugestionfor ) throws NoDataFoundException   {
    	FriendSuggestionList res= new FriendSuggestionList();
    	res=appService.getFriendSuggestionList(friendsugestionfor);
        return ResponseEntity.ok(res).status(200).body(res);
    }
    
}
