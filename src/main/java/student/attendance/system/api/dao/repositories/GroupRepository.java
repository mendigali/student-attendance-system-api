package student.attendance.system.api.dao.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student.attendance.system.api.dao.entities.GroupEntity;
import student.attendance.system.api.dao.rowmappers.GroupEntityRowMapper;

@Repository
public class GroupRepository {

    private final JdbcTemplate jdbcTemplate;

    public GroupRepository(@Qualifier("databaseJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GroupEntity getById(Integer id) {
        String query = "SELECT * FROM groups WHERE id = ?";

        return jdbcTemplate.queryForObject(query, new GroupEntityRowMapper());
    }
}
