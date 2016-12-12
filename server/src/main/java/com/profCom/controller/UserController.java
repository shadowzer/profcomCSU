package com.profCom.controller;

import com.profCom.entity.Category;
import com.profCom.entity.User;
import com.profCom.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") long userID) {
        return service.getByID(userID);
    }

    @RequestMapping(value = "/User", method = RequestMethod.POST)
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        return service.save(user);
    }

    @RequestMapping(value = "/User/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") long id) {
        service.remove(id);
    }
}
