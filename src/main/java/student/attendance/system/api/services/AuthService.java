package student.attendance.system.api.services;

import lombok.RequiredArgsConstructor;
import student.attendance.system.api.dao.entities.UserEntity;
import student.attendance.system.api.dao.repositories.UserRepository;
import student.attendance.system.api.exceptions.APIBadRequestException;
import student.attendance.system.api.models.requests.AuthRegisterStudentRequest;
import student.attendance.system.api.models.requests.AuthRegisterTeacherRequest;
import student.attendance.system.api.models.requests.AuthSigninRequest;
import student.attendance.system.api.models.requests.AuthRegisterUserRequest;
import student.attendance.system.api.models.responses.AuthLoginResponse;
import student.attendance.system.api.models.responses.AuthRegisterResponse;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthLoginResponse authLogin(AuthSigninRequest request) throws APIBadRequestException {
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

    public AuthRegisterResponse authRegister(AuthRegisterUserRequest request) throws APIBadRequestException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new APIBadRequestException("Error: Email is already taken!");
        }

        UserEntity user = new UserEntity(
                null,
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        userRepository.save(user);

        String token = Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());

        user = userRepository.findByEmail(request.getEmail());

        return new AuthRegisterResponse(token, user);
    }

    public AuthRegisterResponse authRegisterStudent(AuthRegisterStudentRequest request) throws APIBadRequestException {
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

        return new AuthRegisterResponse(token, user);
    }

    public AuthRegisterResponse authRegisterTeacher(AuthRegisterTeacherRequest request) throws APIBadRequestException {
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

        return new AuthRegisterResponse(token, user);
    }
}
