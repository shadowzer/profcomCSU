package com.profCom.controller;

import com.profCom.entity.UserGift;
import com.profCom.service.UserGift.UserGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 12.12.2016.
 */

@RestController
public class UserGiftController {

    @Autowired
    private UserGiftService service;

    @RequestMapping(value = "/UserGift", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGift> getAllUserGifts() {
        List<UserGift> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/UserGift/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserGift getUserGift(@PathVariable("id") long UserGiftID) {
        return service.getByID(UserGiftID);
    }

    @RequestMapping(value = "/UserGift", method = RequestMethod.POST)
    @ResponseBody
    public UserGift saveUserGift(@RequestBody UserGift UserGift) {
        return service.save(UserGift);
    }

    @RequestMapping(value = "/UserGift/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/UserGift/searchUsersByGift/{Gid}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGift> getUsersByGift(@PathVariable("Gid") long Gid) {
        return service.findByGift(Gid);
    }

    @RequestMapping(value = "/UserGift/searchGiftsByUser/{Uid}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGift> getGiftsByUser(@PathVariable("Uid") long Uid) {
        return service.findByUser(Uid);
    }
}
