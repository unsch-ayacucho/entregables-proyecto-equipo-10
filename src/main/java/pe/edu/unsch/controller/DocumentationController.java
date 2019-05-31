package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/documentation")
public class DocumentationController {

    @RequestMapping(method = RequestMethod.GET)
    public String documentation(ModelMap model){
        model.addAttribute("message", "Documentation");
        return "documentation/documentation";
    }

}
