package com.profCom.controller.WEBcontroller;

import com.profCom.entity.User;
import com.profCom.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by VolgiNN on 14.12.2016.
 */
@Controller
public class AdminController {
    @Autowired
    UserService us;
    @RequestMapping("/")
    public String mainAdmin(Model model) {
        System.out.println("MAIN ADMIN");
        return "mainAdmin";
    }

    @RequestMapping("/admin/user")
    public String userAdmin(Model model) {
        System.out.println("USER ADMIN");
        return "userAdmin";
    }

    @RequestMapping(value = "/admin/user/create" ,method = RequestMethod.GET)
    public String createUserAdmin(Model model) {
        System.out.println("CREATE USER ADMIN");
        return "createUser";
    }

    @RequestMapping(value = "/admin/user/create" ,method = RequestMethod.POST)
    public String createUserAdminP(Model model, HttpServletRequest request) {
        System.out.println("CREATE USER ADMIN POST");
        User user=new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setSurName(request.getParameter("surname"));
        user.setFulltime(Boolean.parseBoolean(request.getParameter("fulltime")));
        user.setBudget(Boolean.parseBoolean(request.getParameter("budget")));
        user.setStudentGroup(request.getParameter("studentgroup"));
        us.save(user);
        return "createUser";
    }
}
