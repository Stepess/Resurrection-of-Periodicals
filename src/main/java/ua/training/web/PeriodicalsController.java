package ua.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.training.service.UserService;
import ua.training.model.User;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/periodicals")
public class PeriodicalsController {

    public static final String MAX_LONG_AS_STRING  = Constants.MAX_LONG_AS_STRING;
    public static final String RECENT_USERS_LIMIT = "20";

    private final UserService userService;

    @Autowired
    public PeriodicalsController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User()); // to render form
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestPart(value = "profilePicture", required = false) MultipartFile multipartFile,
                                      @Valid User user, Errors errors, RedirectAttributes model) throws IOException {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        if (multipartFile != null && !multipartFile.isEmpty()) {
            multipartFile.transferTo(new File(user.getUsername() + "_" + multipartFile.getOriginalFilename()));
        }

        userService.save(user);
        model.addAttribute("username", user.getUsername());
        model.addFlashAttribute(user);
        return "redirect:/periodicals/{username}";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String profile(@PathVariable String username, Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute(userService.findByUsername(username));
        }
        return "profile";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> users(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = RECENT_USERS_LIMIT) int count
    ) {
       return userService.findUsers(max, count);
    }

}
