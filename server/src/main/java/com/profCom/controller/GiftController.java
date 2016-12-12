package com.profCom.controller;

import com.profCom.entity.Gift;
import com.profCom.entity.Gift;
import com.profCom.service.Gift.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 12.12.2016.
 */

@RestController
public class GiftController {

    @Autowired
    private GiftService service;
    
    @RequestMapping(value = "/Gift", method = RequestMethod.GET)
    @ResponseBody
    public List<Gift> getAllGifts() {
        List<Gift> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/Gift/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/Gift", method = RequestMethod.POST)
    @ResponseBody
    public Gift saveGift(@RequestBody Gift gift) {
        return service.save(gift);
    }
}
