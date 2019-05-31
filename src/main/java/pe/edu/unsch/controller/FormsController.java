package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/forms")
public class FormsController {

    @RequestMapping(method = RequestMethod.GET)
    public String forms(ModelMap model){
        model.addAttribute("message", "Forms");
        return "forms/basic_elements";
    }

}
