package student.attendance.system.api.dao.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student.attendance.system.api.dao.entities.Teacher;

@Repository
public class TeacherRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeacherRepository(@Qualifier("databaseJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Teacher teacher) {
        String query = "INSERT INTO teachers (id, full_name, user_id) VALUES (DEFAULT, ?, ?)";

        jdbcTemplate.update(query, teacher.getFullName(), teacher.getUserId());
    }
}
