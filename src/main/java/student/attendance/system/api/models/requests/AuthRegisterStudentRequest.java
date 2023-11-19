package student.attendance.system.api.models.requests;

import lombok.Data;

@Data
public class AuthRegisterStudentRequest {

    private String email;
    private String password;
    private String fullName;
    private Integer groupId;
}
