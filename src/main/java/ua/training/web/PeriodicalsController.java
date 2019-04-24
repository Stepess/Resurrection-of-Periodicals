package ua.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.training.data.UserRepository;
import ua.training.model.User;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/periodicals")
public class PeriodicalsController {

    private final UserRepository userRepository;

    @Autowired
    public PeriodicalsController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        userRepository.save(user);
        model.addAttribute("username", user.getUsername());
        model.addFlashAttribute(user);
        return "redirect:/periodicals/{username}";
    }

    // TODO add multipart support
    /*@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture,
                                      @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        userRepository.save(user);

        return "redirect:/periodicals/" + user.getUsername();
    }*/

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String profile(@PathVariable String username, Model model) {
        if (!model.containsAttribute("user")) {
            userRepository.findByUsername(username);
        }
        return "profile";
    }

}
