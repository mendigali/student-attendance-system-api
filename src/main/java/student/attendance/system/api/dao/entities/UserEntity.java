package student.attendance.system.api.dao.entities;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import student.attendance.system.api.exceptions.APIBadRequestException;
import student.attendance.system.api.exceptions.APIException;
import student.attendance.system.api.models.UserPrincipal;

@Data
@AllArgsConstructor
public class UserEntity {

    public static final String STUDENT = "STUDENT";
    public static final String TEACHER = "TEACHER";

    private Integer id;
    private String email;
    private String password;
    private String role;

    public static UserPrincipal fromJson(String json) throws APIException {
        try {
            return (UserPrincipal)(new Gson()).fromJson(json, UserPrincipal.class);
        } catch (Exception ex) {
            throw new APIBadRequestException("Cannot convert json to UserPrincipal. json: " + json);
        }
    }
}
