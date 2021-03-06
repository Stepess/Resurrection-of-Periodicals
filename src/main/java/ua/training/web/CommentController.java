package ua.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.service.CommentService;
import ua.training.exception.CommentNotFoundException;
import ua.training.model.Comment;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    public static final String RECENT_COMMENTS_LIMIT = "20";
    public static final String MAX_LONG_AS_STRING = Constants.MAX_LONG_AS_STRING;

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> comments(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                                  @RequestParam(value = "count", defaultValue = RECENT_COMMENTS_LIMIT) int count) {
        return commentService.findComments(max, count);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public String comment(@PathVariable long commentId, Model model) {
        Comment comment = commentService.findOne(commentId);

        if (comment == null) {
            throw new CommentNotFoundException();
        }

        model.addAttribute(comment);
        return "comment";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveComment(Comment comment) {
        commentService.save(comment);
        return "redirect:/comments";
    }

    /*@ExceptionHandler(DuplicateCommentException.class)
    public String handleDuplicateComment() {
        return "error/dublicate";
    }
*/
}
