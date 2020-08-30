package org.codejudge.sb.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.codejude.sb.model.FriendList;
import org.codejude.sb.model.FriendSuggestionList;
import org.codejude.sb.model.PendingFriendRequest;
import org.codejude.sb.model.ResponseCode;
import org.codejude.sb.model.UserResponse;
import org.codejudge.sb.entity.FriendRequest;
import org.codejudge.sb.entity.Friends;
import org.codejudge.sb.entity.User;
import org.codejudge.sb.exception.FriendReqException;
import org.codejudge.sb.exception.NoDataFoundException;
import org.codejudge.sb.exception.UserCreationException;
import org.codejudge.sb.repository.FriendRepo;
import org.codejudge.sb.repository.FriendReqRepo;
import org.codejudge.sb.repository.UserRepository;
import org.codejudge.sb.service.Appservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppserviceImpl implements Appservice {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private FriendReqRepo friendReqst;

	@Autowired
	private FriendRepo frnds;

	@Override
	public UserResponse create(String username) throws UserCreationException {
		User usr = new User();
		UserResponse usrRes = new UserResponse();

		usr.setUsername(username);
		try {
			usr = userRepo.save(usr);
		} catch (Exception e) {
			throw new UserCreationException(e.getMessage());
		}
		usrRes.setUsername(usr.getUsername());
		return usrRes;

	}

	@Override
	public ResponseCode sendFriendRequest(String friendrequestfrom, String friendrequestto) throws FriendReqException {
		FriendRequest frndReq = new FriendRequest();
		ResponseCode resp = new ResponseCode();
		frndReq.setFriendReqFrom(friendrequestfrom);
		frndReq.setFriendReqTo(friendrequestto);
		frndReq.setAccepted(0);
		try {
			List<FriendRequest> fr = (List<FriendRequest>) friendReqst.findfriendReq(friendrequestto);
			if (checkFriendReq(fr, friendrequestfrom)) {
				updateFreindReq(fr, friendrequestto, friendrequestfrom);
				saveConfirmedFriends(friendrequestto, friendrequestfrom);
			} else {
				friendReqst.save(frndReq);
			}

		} catch (Exception e) {
			throw new FriendReqException(e.getMessage());
		}
		resp.setStatus("success");

		return resp;
	}

	private void saveConfirmedFriends(String friendrequestto, String friendrequestfrom) {
		Friends fr = new Friends();
		fr.setFriendReqFrom(friendrequestfrom);
		fr.setFriendReqTo(friendrequestto);
		fr.setFriend(1);
		frnds.save(fr);

	}

	private void updateFreindReq(List<FriendRequest> fr, String friendrequestto, String friendrequestfrom) {
		for (FriendRequest frR : fr) {
			if (frR.getFriendReqTo().equals(friendrequestfrom)) {
				FriendRequest frndReq = new FriendRequest();
				frndReq.setFriendReqFrom(friendrequestfrom);
				frndReq.setFriendReqTo(friendrequestto);
				frndReq.setAccepted(1);
				friendReqst.save(frndReq);
				List<FriendRequest> frt = friendReqst.updateaccepted(friendrequestto, friendrequestfrom);
				frt.get(0).setAccepted(1);
				friendReqst.save(frt.get(0));

			}
		}

	}

	private boolean checkFriendReq(List<FriendRequest> fr, String friendrequestfrom) {
		for (FriendRequest frR : fr) {
			if (frR.getFriendReqTo().equals(friendrequestfrom)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PendingFriendRequest getPendingFriendRequest(String pendingfriendreqfor) throws NoDataFoundException {
		PendingFriendRequest pfr = new PendingFriendRequest();
		List<String> list = new ArrayList<>();
		List<FriendRequest> frt = friendReqst.getpendingFriendReq(pendingfriendreqfor);
		if (frt == null || frt.isEmpty()) {
			throw new NoDataFoundException("No friend request pending");
		}
		for (FriendRequest fr : frt) {
			list.add(fr.getFriendReqFrom());
		}
		pfr.setFriend_requests(list);
		return pfr;
	}

	@Override
	public FriendList getFriendList(String friendlistfor) throws NoDataFoundException {
		FriendList frL = new FriendList();
		List<String> list = new ArrayList<>();
		List<FriendRequest> frt = friendReqst.getFriendList(friendlistfor);
		if (frt == null || frt.isEmpty()) {
			throw new NoDataFoundException("No friend request pending");
		}
		for (FriendRequest fr : frt) {
			list.add(fr.getFriendReqFrom());
		}
		frL.setFriends(list);
		return frL;
	}

	@Override
	public FriendSuggestionList getFriendSuggestionList(String friendsugestionfor) throws NoDataFoundException {
		FriendSuggestion frf = new FriendSuggestion();
		return frf.getFriendSuggestionList(friendsugestionfor, frnds);

	}

}
