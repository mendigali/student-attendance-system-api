package student.attendance.system.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {

    @Operation(summary = "API health check")
    @GetMapping("/check")
    public String getHealthCheck() {
        return "Student Attendance System API is running...";
    }
}
