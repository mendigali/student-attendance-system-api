package student.attendance.system.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import student.attendance.system.api.models.responses.ScheduleAllResponse;
import student.attendance.system.api.services.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "Get schedule for week")
    @GetMapping("/week")
    public List<ScheduleAllResponse> getScheduleWeek(@RequestParam Integer groupId) {
        return scheduleService.getScheduleWeek(groupId);
    }

    @Operation(summary = "Get schedule for today")
    @GetMapping("/today")
    public List<ScheduleAllResponse> getScheduleToday(@RequestParam Integer groupId) {
        return scheduleService.getScheduleToday(groupId);
    }
}
