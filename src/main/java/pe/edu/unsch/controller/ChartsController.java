package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/charts")
public class ChartsController {

    @RequestMapping(method = RequestMethod.GET)
    public String charts(ModelMap model){
        model.addAttribute("message", "Charts");
        return "charts/chartjs";
    }

}
