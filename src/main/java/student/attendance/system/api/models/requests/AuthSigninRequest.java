package student.attendance.system.api.models.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthSigninRequest {

    @NotEmpty(message = "Username is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;
}
