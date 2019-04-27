package ua.training.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.training.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCommentRepository implements CommentRepository {

    private final JdbcOperations jdbcOperations;

    public static final String FIND_ALL_COMMENTS =
            "SELECT * FROM comments WHERE id<? LIMIT ?";
    public static final String FIND_COMMENT =
            "SELECT * FROM comments WHERE id=?";
    public static final String SAVE_COMMENT =
            "insert into comments (user_id, message, date, latitude, longitude) " +
            "values (?, ?, ?, ?, ?)";

    @Autowired
    public JdbcCommentRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Comment> findComments(long max, int count) {
        return jdbcOperations.query(FIND_ALL_COMMENTS, new Object[]{max, count}, this::mapComment);
    }

    @Override
    public Comment findOne(long id) {
        return jdbcOperations.queryForObject(FIND_COMMENT, this::mapComment, id);
    }

    @Override
    public Comment save(Comment comment) {
        jdbcOperations.update(SAVE_COMMENT, comment.getUserId(), comment.getMessage(),
                comment.getDate(), comment.getLatitude(), comment.getLongitude());
        return comment;
    }

    private Comment mapComment(ResultSet rs, int rows) throws SQLException {
        return new Comment(
                rs.getLong("user_id"),
                rs.getString("message"),
                rs.getDate("date"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude")
        );
    }
}


