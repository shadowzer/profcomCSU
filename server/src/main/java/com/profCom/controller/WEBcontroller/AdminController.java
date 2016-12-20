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

import java.io.UnsupportedEncodingException;

import static java.lang.System.out;

/**
 * Created by VolgiNN on 14.12.2016.
 */
@Controller
public class AdminController {
    @Autowired
    UserService us;

    @RequestMapping("/")
    public String mainAdmin(Model model) {
        out.println("MAIN ADMIN");
        return "mainAdmin";
    }

    @RequestMapping("/admin/user")
    public String userAdmin(Model model) {
        out.println("USER ADMIN");
        return "userAdmin";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
    public String createUserAdmin(Model model) {
        out.println("CREATE USER ADMIN");
        return "createUser";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserAdminP(Model model, HttpServletRequest request) {
        out.println("CREATE USER ADMIN POST");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setSurName(request.getParameter("surname"));
        user.setFulltime(Boolean.parseBoolean(request.getParameter("fulltime")));
        user.setBudget(Boolean.parseBoolean(request.getParameter("budget")));
        user.setStudentGroup(request.getParameter("studentgroup"));
        us.save(user);
        model.addAttribute("message", "В БД добавлен пользователь с логином:" + user.getLogin());
        return "message";
    }

    @RequestMapping(value = "/admin/user/search", method = RequestMethod.GET)
    public String searchUserAdmin(Model model, HttpServletRequest request) {
        out.println("SEARCH USER ADMIN");
        User user;
        try {
            if (request.getParameter("login")!=null) {
                user = us.findByLogin(request.getParameter("login"));
                if (user != null) {
                    model = convertUserInModel(user, model);
                    return "user";
                } else
                    model.addAttribute("message", "пользователь с логином:'" + request.getParameter("login") + "'не найден");
            }
        } catch (Exception e) {
            out.println("find login exception:" + e.getMessage());
        }
        try {
            if (request.getParameter("id")!=null) {
                user = us.getByID(Long.parseLong(request.getParameter("id")));
                if (user != null) {
                    model = convertUserInModel(user, model);
                    return "user";
                } else
                    model.addAttribute("message", "пользователь с ID:'" + request.getParameter("id") + "'не найден");
            }
        } catch (Exception e) {
            out.println("find id exception:" + e.getMessage());
        }
        return "searchUser";
    }

    private Model convertUserInModel(User user, Model model) {
        model.addAttribute("id", user.getId());
        model.addAttribute("firstname", user.getFirstName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("surname", user.getSurName());
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("tabnum", user.getTabNum());
        model.addAttribute("studentgroup", user.getStudentGroup());
        if (user.isBudget())
            model.addAttribute("budget", "бюджет");
        else
            model.addAttribute("budget", "не бюджет");
        if (user.isFulltime())
            model.addAttribute("fulltime", "очная");
        else
            model.addAttribute("fulltime", "заочная");
        return model;

    }

    @RequestMapping(value = "/admin/user/updateForm1", method = RequestMethod.GET)
    public String updateUserAdmin(Model model, HttpServletRequest request){
        return "updateUserSearchForm";
    }

    @RequestMapping(value = "/admin/user/updateForm2", method = RequestMethod.GET)
    public String updateUserAdmin2(Model model, HttpServletRequest request){
        User user;
        try {
            if (request.getParameter("login")!=null) {
                user = us.findByLogin(request.getParameter("login"));
                if (user != null) {
                    model = convertUserInModel(user, model);
                    return "updateForm";
                } else
                    model.addAttribute("message", "пользователь с логином:'" + request.getParameter("login") + "'не найден");
            }
        } catch (Exception e) {
            out.println("find login exception:" + e.getMessage());
        }
        try {
            if (request.getParameter("id")!=null) {
                user = us.getByID(Long.parseLong(request.getParameter("id")));
                if (user != null) {
                    model = convertUserInModel(user, model);
                    return "updateForm";
                } else
                    model.addAttribute("message", "пользователь с ID:'" + request.getParameter("id") + "'не найден");
            }
        } catch (Exception e) {
            out.println("find id exception:" + e.getMessage());
        }
        return "updateUserSearchForm";
    }

    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String updateUserAdminP(Model model, HttpServletRequest request) {
        out.println("update USER ADMIN POST"+request.getParameter("id"));

        User user = us.getByID(Long.parseLong(request.getParameter("Uid")));

        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setSurName(request.getParameter("surname"));
        user.setFulltime(Boolean.parseBoolean(request.getParameter("fulltime")));
        user.setBudget(Boolean.parseBoolean(request.getParameter("budget")));
        user.setStudentGroup(request.getParameter("studentgroup"));
        user.setTabNum(request.getParameter("tabnum"));
        us.save(user);
        model.addAttribute("message", "пользователь отредактирован");
        model=convertUserInModel(user,model);
        return "user";
    }

    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
    public String deleteUserAdmin(Model model, HttpServletRequest request){
        return "deleteUser";
    }

    @RequestMapping(value = "/admin/user/delete", method = RequestMethod.POST)
    public String deleteUserAdminP(Model model, HttpServletRequest request){
        User user;
        try {
            if (request.getParameter("login")!=null) {
                user = us.findByLogin(request.getParameter("login"));
                if (user != null) {
                    model.addAttribute("message", "пользователь с логином :"+user.getLogin()+";id:"+user.getId()+"   удален");
                    us.remove(user.getId());
                    return "deleteUser";
                }

            }
        } catch (Exception e) {
            out.println("find login exception:" + e.getMessage());
        }
        try {
            if (request.getParameter("id")!=null) {
                user = us.getByID(Long.parseLong(request.getParameter("id")));
                if (user != null) {
                    model.addAttribute("message", "пользователь с логином :" + user.getLogin() + ";id:" + user.getId() + "   удален");
                    us.remove(user.getId());
                    return "deleteUser";
                }
            }
        } catch (Exception e) {
            out.println("find id exception:" + e.getMessage());
        }
        model.addAttribute("message", "пользователя с указанными параметрами не существует");

        return "deleteUser";
    }

}
