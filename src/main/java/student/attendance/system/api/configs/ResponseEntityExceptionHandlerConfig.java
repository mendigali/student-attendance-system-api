package student.attendance.system.api.configs;

import student.attendance.system.api.exceptions.APIError;
import student.attendance.system.api.exceptions.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIError> handleAPIException(APIException exception) {
        return new ResponseEntity<>(
                new APIError(exception),
                exception.getHttpStatus()
        );
    }
}
