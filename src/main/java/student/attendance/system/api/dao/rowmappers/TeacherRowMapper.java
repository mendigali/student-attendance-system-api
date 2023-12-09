package student.attendance.system.api.dao.rowmappers;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.Teacher;

import java.sql.ResultSet;

public class TeacherRowMapper implements RowMapper<Teacher> {

    @SneakyThrows
    @Override
    public Teacher mapRow(ResultSet rs, int i) {
        return new Teacher(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getInt("user_id")
        );
    }
}
