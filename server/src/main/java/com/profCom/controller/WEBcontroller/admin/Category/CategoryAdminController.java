package com.profCom.controller.WEBcontroller.admin.Category;

import com.profCom.entity.Category;
import com.profCom.entity.User;
import com.profCom.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

import static java.lang.System.out;

/**
 * Created by VolgiNN on 21.12.2016.
 */
@Controller
public class CategoryAdminController {
    @Autowired
    CategoryService cs;

    @RequestMapping("/admin/category")
    public String categoryAdmin(Model model) {
        out.println("CATEGORY ADMIN");
        return "categoryAdmin";
    }

    @RequestMapping(value = "/admin/category/create", method = RequestMethod.GET)
    public String createCategoryAdmin(Model model) {
        out.println("CREATE CATEGORY ADMIN");
        return "createCategoryAdmin";
    }

    @RequestMapping(value = "/admin/category/create", method = RequestMethod.POST)
    public String createCategoryAdminP(Model model, HttpServletRequest request) {
        out.println("CREATE CATEGORY ADMIN POST");
        Category c = new Category();
        out.println("CREATE USER ADMIN POST");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        if (cs.findByName(request.getParameter("nameCategory")) == null) {
            c.setName(request.getParameter("nameCategory"));
            if (c.getName() != "") {
                cs.save(c);
                model.addAttribute("message", "категория '" + c.getName() + "' успешно добавлена в БД");
            } else {
                model.addAttribute("message", "категория не может быть добавлена в БД, так как вы не указали название категории");
            }
        } else {
            model.addAttribute("message", "категория '" + request.getParameter("nameCategory") + "' не может быть добавлена в БД, так как она уже там присутствует");
        }
        return "createCategoryAdmin";
    }

    @RequestMapping(value = "/admin/category/delete", method = RequestMethod.GET)
    public String deleteCategoryAdmin(Model model, HttpServletRequest request) {
        out.println("DELETE CATEGORY ADMIN");
        return "deleteCategoryAdmin";
    }

    @RequestMapping(value = "/admin/category/delete", method = RequestMethod.POST)
    public String deleteCategoryAdminP(Model model, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        Category category;
        try {
            if (request.getParameter("nameCategory") != null) {
                category = cs.findByName(request.getParameter("nameCategory"));
                if (category != null) {
                    cs.remove(category.getId());
                    model.addAttribute("message", "Категория '" + category.getName() + "' удалена");
                    return "deleteCategoryAdmin";
                }
            }
        } catch (Exception e) {
            out.println("find login exception:" + e.getMessage());
        }
        try {
            if (request.getParameter("id") != null) {
                category = cs.getByID(Integer.parseInt(request.getParameter("id")));
                if (category != null) {
                    cs.remove(category.getId());
                    model.addAttribute("message", "Категория '" + category.getName() + "' с iD '" + category.getId() + "' удалена");
                    return "deleteCategoryAdmin";
                }
            }
        } catch (Exception e) {
            out.println("find id exception:" + e.getMessage());
        }
        model.addAttribute("message", "Категории с указанными параметрами не существует");

        return "deleteCategoryAdmin";
    }


    @RequestMapping(value = "/admin/category/update", method = RequestMethod.GET)
    public String updateCategoryAdmin(Model model, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {}
        Category category;
        try {
            if (request.getParameter("nameCategory") != null){
                category = cs.findByName(request.getParameter("nameCategory"));
                if (category != null) {
                    model=convertCategoryInModel(category,model);
                    return "updateCategory";
                }
                model.addAttribute("message", "Категории с указанными параметрами не существует");
            }
        } catch (Exception e) {
            out.println("find login exception:" + e.getMessage());
        }
        try {
            if (request.getParameter("id") != null) {
                category = cs.getByID(Integer.parseInt(request.getParameter("id")));
                if (category != null) {
                    model=convertCategoryInModel(category,model);
                    return "updateCategory";
                }
                model.addAttribute("message", "Категории с указанными параметрами не существует");
            }
        } catch (Exception e) {
            out.println("find id exception:" + e.getMessage());
        }

        return "updateSearchCategory";

    }

    @RequestMapping(value = "/admin/category/update", method = RequestMethod.POST)
    public String updateCategoryAdminP(Model model, HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {}
        Category c=cs.getByID(Integer.parseInt(request.getParameter("id")));
        c.setName(request.getParameter("nameCategory"));
        if (c.getName() != "") {
            cs.save(c);
            model=convertCategoryInModel(c,model);
        } else {
            model=convertCategoryInModel(c,model);
            model.addAttribute("message", "категория не может быть добавлена в БД, так как вы не указали название категории");
        }

        return "updateCategory";
    }

    private Model convertCategoryInModel(Category category,Model model){
        model.addAttribute("id",category.getId());
        model.addAttribute("nameCategory",category.getName());
        return model;
    }

    @RequestMapping(value = "/admin/category/search", method = RequestMethod.GET)
    public String searchCategoryAdmin(Model model, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {}
        Category category;
        try {
            if (request.getParameter("nameCategory") != null){
                category = cs.findByName(request.getParameter("nameCategory"));
                if (category != null) {
                    model=convertCategoryInModel(category,model);
                    return "category";
                }
                model.addAttribute("message", "Категории с указанными параметрами не существует");
            }
        } catch (Exception e) {
            out.println("find login exception:" + e.getMessage());
        }
        try {
            if (request.getParameter("id") != null) {
                category = cs.getByID(Integer.parseInt(request.getParameter("id")));
                if (category != null) {
                    model=convertCategoryInModel(category,model);
                    return "category";
                }
                model.addAttribute("message", "Категории с указанными параметрами не существует");
            }
        } catch (Exception e) {
            out.println("find id exception:" + e.getMessage());
        }

        return "searchCategory";

    }




}
