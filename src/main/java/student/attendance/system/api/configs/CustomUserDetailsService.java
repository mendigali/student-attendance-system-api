package student.attendance.system.api.configs;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import student.attendance.system.api.dao.entities.UserEntity;
import student.attendance.system.api.dao.repositories.UserRepository;
import student.attendance.system.api.models.UserPrincipal;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final Gson gson;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user;
        try {
            user = userRepository.findByEmail(username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User not found");
        }

        // todo использовать шифрование пароля, сейчас пароль хранится в открытом виде
        return User.builder()
                .username(gson.toJson(new UserPrincipal(user.getId().longValue(), user.getEmail(), user.getRole())))
                .password("{noop}" + user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
