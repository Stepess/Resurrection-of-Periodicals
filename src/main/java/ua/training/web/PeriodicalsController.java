package ua.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.training.data.UserRepository;
import ua.training.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/periodicals")
public class PeriodicalsController {

    private final UserRepository userRepository;

    @Autowired
    public PeriodicalsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        userRepository.save(user);

        return "redirect:/periodicals/" + user.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User profile(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

}
