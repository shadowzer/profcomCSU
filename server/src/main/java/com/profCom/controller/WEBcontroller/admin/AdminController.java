package com.profCom.controller.WEBcontroller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.System.out;

/**
 * Created by VolgiNN on 14.12.2016.
 */
@Controller
public class AdminController {

    @RequestMapping("/")
    public String mainAdmin(Model model) {
        out.println("MAIN ADMIN");
        return "mainAdmin";
    }
}
