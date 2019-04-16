package ua.training.data;

import org.springframework.stereotype.Repository;
import ua.training.model.Comment;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CommentRepo implements CommentRepository {

    @Override
    public List<Comment> findComments(long max, int count) {
        return IntStream.range((int)max, count)
                .mapToObj(i -> new Comment("Comment #" + i, new Date()))
                .collect(Collectors.toList());
    }
}