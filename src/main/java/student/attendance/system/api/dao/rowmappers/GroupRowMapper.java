package student.attendance.system.api.dao.rowmappers;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.Group;

import java.sql.ResultSet;

public class GroupRowMapper implements RowMapper<Group> {

    @Override
    @SneakyThrows
    public Group mapRow(ResultSet rs, int i) {
        return new Group(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
