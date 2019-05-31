package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/user-admin-samples")
public class UserAdminController {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model){
        model.addAttribute("message", "Login");
        return "user-admin-samples/login";
    }

    @RequestMapping(value="/login-2", method = RequestMethod.GET)
    public String login2(ModelMap model){
        model.addAttribute("message", "Login2");
        return "user-admin-samples/login-2";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register(ModelMap model){
        model.addAttribute("message", "Register");
        return "user-admin-samples/register";
    }

    @RequestMapping(value="/register-2", method = RequestMethod.GET)
    public String register2(ModelMap model){
        model.addAttribute("message", "Register2");
        return "user-admin-samples/register-2";
    }

    @RequestMapping(value="/lock-screen", method = RequestMethod.GET)
    public String lock_screen(ModelMap model){
        model.addAttribute("message", "Lock Screen");
        return "user-admin-samples/lock-screen";
    }
}
