package student.attendance.system.api.dao.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class AttendanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public AttendanceRepository(@Qualifier("databaseJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAttendance(Integer studentId, Integer scheduleId, LocalDateTime timestamp, String status) {
        String query = "insert into attendances (student_id, schedule_id, timestamp, status) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, studentId, scheduleId, timestamp, status);
    }
}
