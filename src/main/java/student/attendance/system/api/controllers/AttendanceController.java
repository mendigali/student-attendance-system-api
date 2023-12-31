package student.attendance.system.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import student.attendance.system.api.exceptions.APIException;
import student.attendance.system.api.models.requests.AttendanceSaveRequest;
import student.attendance.system.api.models.responses.NullResponse;
import student.attendance.system.api.services.AttendanceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Operation(summary = "Save student attendance")
    @PostMapping("/save")
    public NullResponse postAttendanceSave(@Valid @RequestBody AttendanceSaveRequest request) throws APIException {
        attendanceService.saveAttendance(request.getEmail());
        return new NullResponse();
    }
}
