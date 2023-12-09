package student.attendance.system.api.dao.rowmappers;


import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.Student;

import java.sql.ResultSet;

public class StudentRowMapper implements RowMapper<Student> {

    @SneakyThrows
    @Override
    public Student mapRow(ResultSet rs, int i) {
        return new Student(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getInt("user_id"),
                rs.getInt("group_id")
        );
    }
}
