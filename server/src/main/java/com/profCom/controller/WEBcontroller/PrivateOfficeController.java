package com.profCom.controller.WEBcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by VolgiNN on 14.12.2016.
 */
@Controller

public class PrivateOfficeController {
    String message = "Welcome to Spring MVC!";

    @RequestMapping("/hello")
    public ModelAndView showMessage(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        System.out.println("in controller");
        ModelAndView mv = new ModelAndView("com/profCom/views/holloworld.jsp");
        mv.addObject("message", message);
        mv.addObject("name", "pet'ka");
        return mv;
    }
}
