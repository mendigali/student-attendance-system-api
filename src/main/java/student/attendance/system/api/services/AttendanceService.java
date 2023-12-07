package student.attendance.system.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.attendance.system.api.dao.entities.*;
import student.attendance.system.api.dao.repositories.*;
import student.attendance.system.api.exceptions.APIBadRequestException;
import student.attendance.system.api.exceptions.APIException;
import student.attendance.system.api.exceptions.APINotFoundException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;

    public void saveAttendance(String email) throws APIException {
        UserEntity user = userRepository.findByEmail(email);

        if (!user.getRole().equals(UserEntity.STUDENT)) {
            throw new APIBadRequestException("User must be student!");
        }

        StudentEntity student = studentRepository.findByUserId(user.getId());

        GroupEntity group = groupRepository.getById(student.getGroupId());

        Schedule currentSchedule = scheduleRepository.getScheduleForNow(student.getGroupId());

        if (currentSchedule == null) {
            throw new APINotFoundException("Currently no lesson for group " + group.getName());
        }

        attendanceRepository.saveAttendance(student.getId(), currentSchedule.getScheduleId(), LocalDateTime.now(), Attendance.PRESENT);
    }
}
