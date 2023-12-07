package student.attendance.system.api.dao.rowmappers;


import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.StudentEntity;

import java.sql.ResultSet;

public class StudentEntityRowMapper implements RowMapper<StudentEntity> {

    @SneakyThrows
    @Override
    public StudentEntity mapRow(ResultSet rs, int i) {
        return new StudentEntity(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getInt("user_id"),
                rs.getInt("group_id")
        );
    }
}
