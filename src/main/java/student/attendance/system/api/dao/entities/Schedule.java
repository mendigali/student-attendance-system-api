package student.attendance.system.api.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Schedule {

    private Integer scheduleId;
    private LocalDateTime startTime;
    private Integer groupId;
    private String groupName;
    private Integer courseId;
    private String courseName;
    private Integer classroomId;
    private String classroom;
    private Integer teacherId;
    private String teacherName;
}
