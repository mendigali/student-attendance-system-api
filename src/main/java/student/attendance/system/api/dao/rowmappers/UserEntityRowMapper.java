package student.attendance.system.api.dao.rowmappers;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEntityRowMapper implements RowMapper<UserEntity> {

    @SneakyThrows
    @Override
    public UserEntity mapRow(ResultSet rs, int i) throws SQLException {
        return new UserEntity(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
        );
    }
}
