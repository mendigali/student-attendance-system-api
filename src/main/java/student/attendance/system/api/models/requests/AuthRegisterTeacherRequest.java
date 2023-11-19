package student.attendance.system.api.models.requests;

import lombok.Data;

@Data
public class AuthRegisterTeacherRequest {

    private String email;
    private String password;
    private String fullName;
}
