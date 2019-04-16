package ua.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.training.data.CommentRepository;
import ua.training.model.Comment;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    public static final int RECENT_COMMENTS_LIMIT = 20;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> comments() {
        return commentRepository.findComments(Long.MAX_VALUE, RECENT_COMMENTS_LIMIT);
    }

}
