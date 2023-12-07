package student.attendance.system.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import student.attendance.system.api.exceptions.APIBadRequestException;
import student.attendance.system.api.models.requests.AuthRegisterStudentRequest;
import student.attendance.system.api.models.requests.AuthRegisterTeacherRequest;
import student.attendance.system.api.models.requests.AuthSigninRequest;
import student.attendance.system.api.models.requests.AuthRegisterUserRequest;
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
    public AuthLoginResponse authLogin(@Valid @RequestBody AuthSigninRequest request) throws APIBadRequestException {
        return authService.authLogin(request);
    }

//    @Operation(summary = "Register new user")
//    @PostMapping("/register/user")
//    public AuthRegisterResponse authRegisterUser(@Valid @RequestBody AuthRegisterUserRequest request) throws APIBadRequestException {
//        return authService.authRegister(request);
//    }

    @Operation(summary = "Register new student")
    @PostMapping("/register/student")
    public AuthRegisterResponse authRegisterStudent(@Valid @RequestBody AuthRegisterStudentRequest request) throws APIBadRequestException {
        return authService.authRegisterStudent(request);
    }

    @Operation(summary = "Register new teacher")
    @PostMapping("/register/teacher")
    public AuthRegisterResponse authRegisterTeacher(@Valid @RequestBody AuthRegisterTeacherRequest request) throws APIBadRequestException {
        return authService.authRegisterTeacher(request);
    }
}
