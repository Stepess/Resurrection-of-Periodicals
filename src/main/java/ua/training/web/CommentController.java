package ua.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.data.CommentRepository;
import ua.training.model.Comment;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    public static final String RECENT_COMMENTS_LIMIT = "20";
    public static final String MAX_LONG_AS_STRING = "9223372036854775807";
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> comments(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                                  @RequestParam(value = "count", defaultValue = RECENT_COMMENTS_LIMIT) int count) {
        return commentRepository.findComments(max, count);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public String comment(@PathVariable long commentId, Model model) {
        model.addAttribute(commentRepository.findOne(commentId));
        return "comment";
    }

}
