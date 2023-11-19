package student.attendance.system.api.models.requests;

import lombok.Data;

@Data
public class AuthRegisterUserRequest {

    private String email;
    private String password;
    private String role;
}
