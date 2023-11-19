package student.attendance.system.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import student.attendance.system.api.dao.entities.UserEntity;
import student.attendance.system.api.exceptions.APIException;
import student.attendance.system.api.models.requests.AttendanceSaveRequest;
import student.attendance.system.api.models.responses.NullResponse;
import student.attendance.system.api.services.AttendanceService;

import java.security.Principal;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Operation(summary = "Save student attendance")
    @PostMapping("/save")
    public NullResponse postAttendanceSave(@RequestBody AttendanceSaveRequest request, Principal principal) throws APIException {
        attendanceService.saveAttendance(UserEntity.fromJson(principal.getName()).getId().intValue(), request.getScheduleId());
        return new NullResponse();
    }
}
