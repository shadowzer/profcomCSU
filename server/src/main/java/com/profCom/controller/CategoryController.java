package com.profCom.controller;

import com.profCom.entity.Category;
import com.profCom.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 09.12.2016.
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/Category", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategories() {
        List<Category> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/Category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategory(@PathVariable("id") Integer CategoryID) {
        return service.getByID(CategoryID);
    }

    @RequestMapping(value = "/Category", method = RequestMethod.POST)
    @ResponseBody
    public Category saveCategory(@RequestBody Category category) {
        return service.save(category);
    }

    @RequestMapping(value = "/Category/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }
}
