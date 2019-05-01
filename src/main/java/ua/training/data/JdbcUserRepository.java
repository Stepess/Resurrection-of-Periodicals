package ua.training.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.training.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserRepository {

    private JdbcOperations jdbcOperations;

    public static final String FIND_USER_BY_USERNAME =
            "SELECT * FROM users WHERE username = ?";

    public static final String INSERT_USER =
            "INSERT INTO users (username, password, email, firstName, lastName, enabled) " +
                    "values (?, ?, ?, ?, ?, ?)";

    public static final String FIND_ALL_USERS =
            "SELECT * FROM users WHERE id<? LIMIT ?";

    @Autowired
    public JdbcUserRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public User save(User user) {
        jdbcOperations.update(INSERT_USER, user.getUsername(), user.getLastName(),
                user.getEmail(), user.getFirstName(), user.getLastName(), true);
        return user;
    }

    public User findByUsername(String username) {
        return jdbcOperations.queryForObject(FIND_USER_BY_USERNAME, this::mapUser, username);
    }

    public List<User> findAll(long max, int count) {
        return jdbcOperations.query(FIND_ALL_USERS, new Object[] {max, count}, this::mapUser);
    }

    private User mapUser(ResultSet rs, int rows) throws SQLException {
        return new User(rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("registration_date").toLocalDate());
    }
}
