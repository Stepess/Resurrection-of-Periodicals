package ua.training.service;

import ua.training.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findComments(long max, int count);

    Comment findOne(long id);

    Comment save(Comment comment);

}
