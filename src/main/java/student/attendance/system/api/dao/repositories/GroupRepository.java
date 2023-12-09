package student.attendance.system.api.dao.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student.attendance.system.api.dao.entities.Group;
import student.attendance.system.api.dao.rowmappers.GroupRowMapper;

@Repository
public class GroupRepository {

    private final JdbcTemplate jdbcTemplate;

    public GroupRepository(@Qualifier("databaseJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Group getById(Integer id) {
        String query = "SELECT * FROM groups WHERE id = ?";

        return jdbcTemplate.queryForObject(query, new GroupRowMapper(), id);
    }
}
