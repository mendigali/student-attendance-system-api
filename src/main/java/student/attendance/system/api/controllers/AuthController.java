package student.attendance.system.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import student.attendance.system.api.exceptions.APIBadRequestException;
import student.attendance.system.api.models.requests.AuthRegisterStudentRequest;
import student.attendance.system.api.models.requests.AuthRegisterTeacherRequest;
import student.attendance.system.api.models.requests.AuthLoginRequest;
import student.attendance.system.api.models.responses.AuthLoginResponse;
import student.attendance.system.api.models.responses.AuthRegisterResponse;
import student.attendance.system.api.services.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public AuthLoginResponse postAuthLogin(@Valid @RequestBody AuthLoginRequest request) throws APIBadRequestException {
        return authService.postAuthLogin(request);
    }

    @Operation(summary = "Register new student")
    @PostMapping("/register/student")
    public AuthRegisterResponse postAuthRegisterStudent(@Valid @RequestBody AuthRegisterStudentRequest request) throws APIBadRequestException {
        return authService.postAuthRegisterStudent(request);
    }

    @Operation(summary = "Register new teacher")
    @PostMapping("/register/teacher")
    public AuthRegisterResponse postAuthRegisterTeacher(@Valid @RequestBody AuthRegisterTeacherRequest request) throws APIBadRequestException {
        return authService.postAuthRegisterTeacher(request);
    }
}
