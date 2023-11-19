package student.attendance.system.api.models.responses;

import student.attendance.system.api.dao.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRegisterResponse {

    private String token;
    private UserEntity user;
}
