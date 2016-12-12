package com.profCom.controller;

import com.profCom.entity.Event;
import com.profCom.entity.User;
import com.profCom.entity.UserEvent;
import com.profCom.service.UserEvent.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 11.12.2016.
 */
public class UserEventController {
    @Autowired
    private UserEventService UEservice;

    @RequestMapping(value = "/UserEvent/{Uid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getEventsByUid(@PathVariable("Uid") Long Uid){
        return UEservice.getEventsByUid(Uid);
    }

    @RequestMapping(value = "/UserEvent", method = RequestMethod.POST)
    @ResponseBody
    public UserEvent save(@RequestBody UserEvent userUserEvent) throws Exception {
        return UEservice.save(userUserEvent);
    }

    @RequestMapping("/UserEvent/getUsersByEvent/{Eid}")
    @ResponseBody
    public List<User> getUsersByEventId(@PathVariable("Eid") Long Eid) {
       return UEservice.getUsersByEventId(Eid);
    }
}
