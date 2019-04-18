package ua.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.data.CommentRepository;
import ua.training.exception.CommentNotFoundException;
import ua.training.exception.DuplicateCommentException;
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
        Comment comment = commentRepository.findOne(commentId);

        if (comment == null) {
            throw new CommentNotFoundException();
        }

        model.addAttribute(comment);
        return "comment";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveComment(Comment comment) {
        commentRepository.save(comment);
        return "redirect:/comments";
    }

    /*@ExceptionHandler(DuplicateCommentException.class)
    public String handleDuplicateComment() {
        return "error/dublicate";
    }
*/
}
