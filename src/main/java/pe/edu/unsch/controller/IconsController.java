package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/icons")
public class IconsController {

    @RequestMapping(method = RequestMethod.GET)
    public String icons(ModelMap model){
        model.addAttribute("message", "Icons");
        return "icons/themify";
    }

}
