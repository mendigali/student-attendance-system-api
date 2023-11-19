package student.attendance.system.api.exceptions;

import lombok.Getter;

@Getter
public class APIError {
    private final String message;
    private final String timestamp;
    private final String httpStatus;

    public APIError(APIException exception) {
        this.message = exception.getMessage();
        this.timestamp = exception.getTimestamp();
        this.httpStatus = String.valueOf(exception.getHttpStatus().value());
    }
}
