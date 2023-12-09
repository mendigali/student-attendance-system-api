package student.attendance.system.api.services;

import lombok.RequiredArgsConstructor;
import student.attendance.system.api.dao.entities.Student;
import student.attendance.system.api.dao.entities.Teacher;
import student.attendance.system.api.dao.entities.UserEntity;
import student.attendance.system.api.dao.repositories.StudentRepository;
import student.attendance.system.api.dao.repositories.TeacherRepository;
import student.attendance.system.api.dao.repositories.UserRepository;
import student.attendance.system.api.exceptions.APIBadRequestException;
import student.attendance.system.api.models.requests.AuthRegisterStudentRequest;
import student.attendance.system.api.models.requests.AuthRegisterTeacherRequest;
import student.attendance.system.api.models.requests.AuthLoginRequest;
import student.attendance.system.api.models.responses.AuthLoginResponse;
import student.attendance.system.api.models.responses.AuthRegisterResponse;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public AuthLoginResponse postAuthLogin(AuthLoginRequest request) throws APIBadRequestException {
        if (!userRepository.existsByEmail(request.getEmail())) {
            throw new APIBadRequestException("Error: User doesn't exist!");
        }

        UserEntity user = userRepository.findByEmail(request.getEmail());

        if (!user.getPassword().equals(request.getPassword())) {
            throw new APIBadRequestException("Error: Password is incorrect!");
        }

        String token = Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());

        return new AuthLoginResponse(token, user);
    }

    public AuthRegisterResponse postAuthRegisterStudent(AuthRegisterStudentRequest request) throws APIBadRequestException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new APIBadRequestException("Error: Email is already taken!");
        }

        UserEntity user = new UserEntity(
                null,
                request.getEmail(),
                request.getPassword(),
                UserEntity.STUDENT
        );

        userRepository.save(user);

        String token = Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());

        user = userRepository.findByEmail(request.getEmail());

        Student student = new Student(
                null,
                request.getFullName(),
                user.getId(),
                request.getGroupId()
        );

        studentRepository.save(student);

        return new AuthRegisterResponse(token, user);
    }

    public AuthRegisterResponse postAuthRegisterTeacher(AuthRegisterTeacherRequest request) throws APIBadRequestException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new APIBadRequestException("Error: Email is already taken!");
        }

        UserEntity user = new UserEntity(
                null,
                request.getEmail(),
                request.getPassword(),
                UserEntity.TEACHER
        );

        userRepository.save(user);

        String token = Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());

        user = userRepository.findByEmail(request.getEmail());

        Teacher teacher = new Teacher(
                null,
                request.getFullName(),
                user.getId()
        );

        teacherRepository.save(teacher);

        return new AuthRegisterResponse(token, user);
    }
}
