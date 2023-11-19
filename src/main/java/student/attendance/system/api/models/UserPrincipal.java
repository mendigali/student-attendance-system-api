package student.attendance.system.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserPrincipal implements Serializable {

    private Long id;
    private String login;
    private String role;

}
