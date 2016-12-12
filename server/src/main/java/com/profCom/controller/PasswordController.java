package com.profCom.controller;

import com.profCom.entity.Password;
import com.profCom.service.Password.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by VolgiNN on 08.12.2016.
 */

@RestController
public class PasswordController {
    @Autowired
    private PasswordService service;

    @RequestMapping(value = "/Password/{login}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public long autorizate(@PathVariable("login") String login,@PathVariable("password") String password) {
       return service.autorizate(login,password);
    }


    @RequestMapping(value = "/Password", method = RequestMethod.POST)
    @ResponseBody
    public Password savePassword(@RequestBody Password password) throws Exception {
        if(service.getByID(password.login)==null)
        return service.save(password);
        else throw new Exception("this login is used by another user");
    }

    @RequestMapping(value = "/Password/{login}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable String login) throws Exception {

        if(service.getByID(login)!=null)
        service.remove(login);
        else throw new Exception("user with this login doesn't exist");
    }
}
