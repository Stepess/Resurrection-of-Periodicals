package ua.training.data;

import ua.training.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findComments(long max, int count);

    Comment findOne(long id);

    void save(Comment comment);

}
