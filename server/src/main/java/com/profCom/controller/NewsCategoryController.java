package com.profCom.controller;

import com.profCom.entity.NewsCategory;
import com.profCom.service.NewsCategory.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 11.12.2016.
 */
@RestController
public class NewsCategoryController {
    @Autowired
    private NewsCategoryService service;

    @RequestMapping(value = "/NewsCategory", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsCategory> getAllNewsCategory() {
        List<NewsCategory> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/NewsCategory/{id}", method = RequestMethod.GET)
    @ResponseBody
    public NewsCategory getNewsCategory(@PathVariable("id") long newsCategoryID) {
        return service.getByID(newsCategoryID);
    }
//НАПИСАТЬ ФУНКЦИИ ПОИСКА НОВСТЕЙ ПО КАТЕГОРИИ
    @RequestMapping(value = "/NewsCategory", method = RequestMethod.POST)
    @ResponseBody
    public NewsCategory save(@RequestBody NewsCategory newsCategory) throws Exception {
        return service.save(newsCategory);
    }

    @RequestMapping(value = "/NewsCategory/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }
}
