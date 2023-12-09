package student.attendance.system.api.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    private Integer id;
    private String fullName;
    private Integer userId;
}
