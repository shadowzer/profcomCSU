package com.profCom.controller.AndroidControllers;

import com.profCom.entity.Category;
import com.profCom.entity.UserCategory;
import com.profCom.service.Category.CategoryService;
import com.profCom.service.User.UserService;
import com.profCom.service.UserCategory.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 10.12.2016.
 */
@RestController
public class UserCategoryController {
    @Autowired
    private UserCategoryService service;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/User/Category/{Uid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getUserCategories(@PathVariable("Uid") Long Uid){
        return service.getUserCategories(Uid);
    }

    @RequestMapping(value = "/UserCategory", method = RequestMethod.POST)
    @ResponseBody
    public UserCategory save(@RequestBody UserCategory userCategory) throws Exception {
        return service.save(userCategory);
    }

    @RequestMapping(value = "/UserCategory/{Uid}/{Cid}", method = RequestMethod.DELETE)
    @ResponseBody
    public void remove(@PathVariable("Uid") Long Uid,@PathVariable("Cid") Integer Cid) throws Exception {
        service.removeByUidAndCid(Uid,Cid);
    }
}
