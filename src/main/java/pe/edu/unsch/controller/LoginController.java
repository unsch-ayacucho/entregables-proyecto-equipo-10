package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/login")
public class LoginController {

  @RequestMapping(method = RequestMethod.GET)
  public String index(ModelMap model){
    model.addAttribute("title", "Login");
    return "login/index";
  }
}
