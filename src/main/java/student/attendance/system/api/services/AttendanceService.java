package student.attendance.system.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.attendance.system.api.dao.entities.Attendance;
import student.attendance.system.api.dao.repositories.AttendanceRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public void saveAttendance(Integer studentId, Integer scheduleId) {
        attendanceRepository.saveAttendance(studentId, scheduleId, LocalDateTime.now(), Attendance.PRESENT);
    }
}
