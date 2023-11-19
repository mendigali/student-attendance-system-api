package student.attendance.system.api.exceptions;

import org.springframework.http.HttpStatus;

public class APINotFoundException extends APIException {

    public APINotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
