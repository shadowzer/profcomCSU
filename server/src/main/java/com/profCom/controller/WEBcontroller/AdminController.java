package com.profCom.controller.WEBcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by VolgiNN on 14.12.2016.
 */
@Controller
public class AdminController {
    @RequestMapping("/")
    public String showMessage(Model model) {
        System.out.println("in controller");
        ModelAndView mv = new ModelAndView("index");
        return "index";
    }
}
