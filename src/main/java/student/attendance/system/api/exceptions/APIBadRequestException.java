package student.attendance.system.api.exceptions;

import org.springframework.http.HttpStatus;

public class APIBadRequestException extends APIException {

    public APIBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
