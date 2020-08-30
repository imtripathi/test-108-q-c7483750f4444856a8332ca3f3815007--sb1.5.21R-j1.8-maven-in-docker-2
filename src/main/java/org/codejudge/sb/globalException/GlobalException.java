package org.codejudge.sb.globalException;

import org.codejude.sb.model.ErrorResp;
import org.codejudge.sb.exception.BadDataException;
import org.codejudge.sb.exception.FriendReqException;
import org.codejudge.sb.exception.NoDataFoundException;
import org.codejudge.sb.exception.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UserCreationException.class)
    protected ResponseEntity<Error> handleException(UserCreationException ex) {
       ErrorResp myError = new ErrorResp();
            myError.setReason(ex.getMessage());
            myError.setStatus("failure");
       return new ResponseEntity(myError,
                               HttpStatus.valueOf(400));
    }
	
	@ExceptionHandler(FriendReqException.class)
    protected ResponseEntity<Error> handleExceptionFriend(FriendReqException ex) {
       ErrorResp myError = new ErrorResp();
            myError.setReason(ex.getMessage());
            myError.setStatus("failure");
       return new ResponseEntity(myError,
                               HttpStatus.valueOf(400));
    }
	
	@ExceptionHandler(NoDataFoundException.class)
    protected ResponseEntity<Error> handleExceptionFriend(NoDataFoundException ex) {
       ErrorResp myError = new ErrorResp();
            myError.setReason(ex.getMessage());
            myError.setStatus("failure");
       return new ResponseEntity(myError,
                               HttpStatus.valueOf(404));
    }
	
	@ExceptionHandler(BadDataException.class)
    protected ResponseEntity<Error> handleExceptionFriend(BadDataException ex) {
       ErrorResp myError = new ErrorResp();
            myError.setReason(ex.getMessage());
            myError.setStatus("failure");
       return new ResponseEntity(myError,
                               HttpStatus.valueOf(400));
    }


}
