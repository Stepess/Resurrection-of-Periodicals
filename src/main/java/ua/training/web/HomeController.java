package ua.training.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String login() {
        return "auth";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String processLoging() {
        return "profile";
    }
}
