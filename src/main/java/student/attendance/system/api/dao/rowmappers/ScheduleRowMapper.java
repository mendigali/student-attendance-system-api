package student.attendance.system.api.dao.rowmappers;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import student.attendance.system.api.dao.entities.Schedule;

import java.sql.ResultSet;

public class ScheduleRowMapper implements RowMapper<Schedule> {

    @SneakyThrows
    @Override
    public Schedule mapRow(ResultSet rs, int i) {
        return new Schedule(
                rs.getInt("schedule_id"),
                rs.getTimestamp("start_time").toLocalDateTime(),
                rs.getInt("group_id"),
                rs.getString("group_name"),
                rs.getInt("course_id"),
                rs.getString("course_name"),
                rs.getInt("classroom_id"),
                rs.getString("classroom"),
                rs.getInt("teacher_id"),
                rs.getString("teacher_name")
        );
    }
}
