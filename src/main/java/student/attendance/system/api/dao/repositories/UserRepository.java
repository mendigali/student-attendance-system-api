package student.attendance.system.api.dao.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import student.attendance.system.api.dao.entities.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import student.attendance.system.api.dao.rowmappers.UserEntityRowMapper;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(@Qualifier("databaseJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(UserEntity userEntity) {
        String query = "INSERT INTO users (id, email, password, role) VALUES (DEFAULT, ?, ?, ?)";

        jdbcTemplate.update(query, userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
    }

    public UserEntity findByEmail(String email) {
        try {
            String query = "SELECT * FROM users WHERE email = ?";

            return jdbcTemplate.queryForObject(query, new UserEntityRowMapper(), email);
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultDataAccessException("User not found with email: " + email, 1);
        }
    }

    public Boolean existsByEmail(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
        return count != null && count > 0;
    }

    public List<String> findRolesByEmail(String email) {
        String query = "SELECT r.name FROM users u JOIN users_roles ur ON u.id = ur.user_id JOIN roles r ON ur.role_id = r.id WHERE u.email = ?";
        return jdbcTemplate.query(query, new Object[]{email}, (rs, rowNum) -> rs.getString("name"));
    }
}
