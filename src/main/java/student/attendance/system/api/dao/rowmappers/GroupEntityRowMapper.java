package student.attendance.system.api.dao.rowmappers;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.GroupEntity;

import java.sql.ResultSet;

public class GroupEntityRowMapper implements RowMapper<GroupEntity> {

    @Override
    @SneakyThrows
    public GroupEntity mapRow(ResultSet rs, int i) {
        return new GroupEntity(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
