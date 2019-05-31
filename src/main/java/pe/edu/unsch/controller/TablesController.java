package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/tables")
public class TablesController {

    @RequestMapping(method = RequestMethod.GET)
    public String tables(ModelMap model){
        model.addAttribute("message", "Tables");
        return "tables/basic_table";
    }

}
