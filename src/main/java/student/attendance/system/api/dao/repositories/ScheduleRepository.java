package student.attendance.system.api.dao.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import student.attendance.system.api.dao.entities.Schedule;
import student.attendance.system.api.dao.rowmappers.ScheduleRowMapper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ScheduleRepository(@Qualifier("databaseJdbcTemplate") JdbcTemplate jdbcTemplate,
                              @Qualifier("databaseNamedJdbc") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Schedule> getScheduleForWeek(Integer groupId) {
        try {
            String query = "SET TIME ZONE 'Asia/Almaty'; " +
                    "select schedules.id as schedule_id, " +
                    "              schedules.timestamp as start_time, " +
                    "              courses.id as course_id, " +
                    "              courses.name as course_name, " +
                    "              teachers.id as teacher_id, " +
                    "              teachers.full_name as teacher_name, " +
                    "              classrooms.id as classroom_id, " +
                    "              classrooms.number as classroom, " +
                    "              groups.id as group_id, " +
                    "              groups.name as group_name " +
                    "from schedules " +
                    "join courses on schedules.course_id = courses.id " +
                    "join teachers on courses.teacher_id = teachers.id " +
                    "join classrooms on schedules.classroom_id = classrooms.id " +
                    "join groups on schedules.group_id = groups.id " +
                    "where groups.id = :groupId " +
                    "and schedules.timestamp >= :weekStart " +
                    "and schedules.timestamp <= :weekEnd " +
                    "order by schedules.timestamp";

            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("groupId", groupId);

            LocalDate currentDate = LocalDate.now();
            LocalDate weekStart = currentDate.with(DayOfWeek.MONDAY);
            LocalDate weekEnd = currentDate.with(DayOfWeek.SUNDAY);
            LocalDateTime weekStartTimestamp = weekStart.atStartOfDay();
            LocalDateTime weekEndTimestamp = weekEnd.atTime(LocalTime.MAX);

            paramsMap.put("weekStart", weekStartTimestamp);
            paramsMap.put("weekEnd", weekEndTimestamp);

            return namedParameterJdbcTemplate.query(query, paramsMap, new ScheduleRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw e;
        }
    }

    public List<Schedule> getScheduleForToday(Integer groupId) {
        try {
            String query = "SET TIME ZONE 'Asia/Almaty'; " +
                    "select schedules.id as schedule_id, " +
                    "              schedules.timestamp as start_time, " +
                    "              courses.id as course_id, " +
                    "              courses.name as course_name, " +
                    "              teachers.id as teacher_id, " +
                    "              teachers.full_name as teacher_name, " +
                    "              classrooms.id as classroom_id, " +
                    "              classrooms.number as classroom, " +
                    "              groups.id as group_id, " +
                    "              groups.name as group_name " +
                    "from schedules " +
                    "join courses on schedules.course_id = courses.id " +
                    "join teachers on courses.teacher_id = teachers.id " +
                    "join classrooms on schedules.classroom_id = classrooms.id " +
                    "join groups on schedules.group_id = groups.id " +
                    "where groups.id = :groupId AND DATE(schedules.timestamp) = :currentDate " +
                    "order by schedules.timestamp";

            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("groupId", groupId);

            LocalDate currentDate = LocalDate.now();
            paramsMap.put("currentDate", currentDate);

            return namedParameterJdbcTemplate.query(query, paramsMap, new ScheduleRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw e;
        }
    }

    public Schedule getScheduleForNow(Integer groupId) {
        try {
            String query = "SET TIME ZONE 'Asia/Almaty'; " +
                    "SELECT schedules.id AS schedule_id, " +
                    "              schedules.timestamp AS start_time, " +
                    "              courses.id AS course_id, " +
                    "              courses.name AS course_name, " +
                    "              teachers.id AS teacher_id, " +
                    "              teachers.full_name AS teacher_name, " +
                    "              classrooms.id AS classroom_id, " +
                    "              classrooms.number AS classroom, " +
                    "              groups.id AS group_id, " +
                    "              groups.name AS group_name " +
                    "FROM schedules " +
                    "JOIN courses ON schedules.course_id = courses.id " +
                    "JOIN teachers ON courses.teacher_id = teachers.id " +
                    "JOIN classrooms ON schedules.classroom_id = classrooms.id " +
                    "JOIN groups ON schedules.group_id = groups.id " +
                    "WHERE groups.id = :groupId " +
                    "      AND schedules.timestamp < CURRENT_TIMESTAMP " +
                    "      AND (schedules.timestamp + INTERVAL '50 minutes') > CURRENT_TIMESTAMP " +
                    "ORDER BY schedules.timestamp " +
                    "LIMIT 1";

            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("groupId", groupId);

            return namedParameterJdbcTemplate.queryForObject(query, paramsMap, new ScheduleRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
