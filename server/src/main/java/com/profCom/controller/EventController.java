package com.profCom.controller;

import com.profCom.entity.Event;
import com.profCom.service.Event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 11.12.2016.
 */@RestController
public class EventController {
    @Autowired
    private EventService service;

    @RequestMapping(value = "/Event", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getAllEvents() {
        return service.getAll();
    }

    @RequestMapping(value = "/Event/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Event getEvent(@PathVariable("id") long EventID) {
        return service.getByID(EventID);
    }

    @RequestMapping(value = "/Event", method = RequestMethod.POST)
    @ResponseBody
    public Event saveEvent(@RequestBody Event Event) {
        return service.save(Event);
    }

    @RequestMapping(value = "/Event/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }
}
