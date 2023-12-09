package student.attendance.system.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.attendance.system.api.dao.entities.Schedule;
import student.attendance.system.api.dao.repositories.ScheduleRepository;
import student.attendance.system.api.models.responses.ScheduleAllResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<ScheduleAllResponse> getScheduleWeek(Integer groupId) {
        List<Schedule> schedules = scheduleRepository.getScheduleForWeek(groupId);

        return schedules.stream()
                .map(schedule -> new ScheduleAllResponse(
                        schedule.getScheduleId(),
                        schedule.getStartTime(),
                        schedule.getStartTime().plusMinutes(50),
                        schedule.getCourseId(),
                        schedule.getCourseName(),
                        schedule.getTeacherId(),
                        schedule.getTeacherName(),
                        schedule.getGroupId(),
                        schedule.getGroupName(),
                        schedule.getClassroomId(),
                        schedule.getClassroom()
                ))
                .collect(Collectors.toList());
    }

    public List<ScheduleAllResponse> getScheduleToday(Integer groupId) {
        List<Schedule> schedules = scheduleRepository.getScheduleForToday(groupId);

        return schedules.stream()
                .map(schedule -> new ScheduleAllResponse(
                        schedule.getScheduleId(),
                        schedule.getStartTime(),
                        schedule.getStartTime().plusMinutes(50),
                        schedule.getCourseId(),
                        schedule.getCourseName(),
                        schedule.getTeacherId(),
                        schedule.getTeacherName(),
                        schedule.getGroupId(),
                        schedule.getGroupName(),
                        schedule.getClassroomId(),
                        schedule.getClassroom()
                ))
                .collect(Collectors.toList());
    }
}
