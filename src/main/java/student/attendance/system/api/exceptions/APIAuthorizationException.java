package student.attendance.system.api.exceptions;

import org.springframework.http.HttpStatus;

public class APIAuthorizationException extends APIException {

    public APIAuthorizationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
