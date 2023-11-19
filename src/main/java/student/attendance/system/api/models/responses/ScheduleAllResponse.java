package student.attendance.system.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScheduleAllResponse {

    private Integer scheduleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer courseId;
    private String courseName;
    private Integer teacherId;
    private String teacherName;
    private Integer groupId;
    private String groupName;
    private Integer classroomId;
    private String classroom;
}
