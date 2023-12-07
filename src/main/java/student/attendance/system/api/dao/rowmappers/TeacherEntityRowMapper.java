package student.attendance.system.api.dao.rowmappers;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.TeacherEntity;

import java.sql.ResultSet;

public class TeacherEntityRowMapper implements RowMapper<TeacherEntity> {

    @SneakyThrows
    @Override
    public TeacherEntity mapRow(ResultSet rs, int i) {
        return new TeacherEntity(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getInt("user_id")
        );
    }
}
