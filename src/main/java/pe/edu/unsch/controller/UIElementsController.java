package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/ui-elements")
public class UIElementsController {

    @RequestMapping(value="/buttons", method = RequestMethod.GET)
    public String buttons(ModelMap model){
        model.addAttribute("message", "Buttons");
        return "ui-elements/buttons";
    }

    @RequestMapping(value="/typography", method = RequestMethod.GET)
    public String typography(ModelMap model){
        model.addAttribute("message", "Typography");
        return "ui-elements/typography";
    }
}
