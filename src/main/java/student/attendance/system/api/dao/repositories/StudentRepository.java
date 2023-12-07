package student.attendance.system.api.dao.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student.attendance.system.api.dao.entities.StudentEntity;
import student.attendance.system.api.dao.rowmappers.StudentEntityRowMapper;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(@Qualifier("databaseJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(StudentEntity studentEntity) {
        String query = "INSERT INTO students (id, full_name, user_id, group_id) VALUES (DEFAULT, ?, ?, ?)";

        jdbcTemplate.update(query, studentEntity.getFullName(), studentEntity.getUserId(), studentEntity.getGroupId());
    }

    public StudentEntity findByUserId(Integer userId) {
        String query = "SELECT * FROM students WHERE user_id = ?";

        return jdbcTemplate.queryForObject(query, new StudentEntityRowMapper(), userId);
    }
}
