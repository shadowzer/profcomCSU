package com.profCom.controller;

import com.profCom.entity.FAQObject;
import com.profCom.service.FAQObject.FAQObjectService;
import com.profCom.sysLogic.StringIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 10.12.2016.
 */
@RestController
public class FAQObjectController {
    @Autowired
    private FAQObjectService service;

    @RequestMapping(value = "/FAQObject", method = RequestMethod.GET)
    @ResponseBody
    public List<FAQObject> getAllFAQObject() {
        List<FAQObject> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/FAQObject/{id}", method = RequestMethod.GET)
    @ResponseBody
    public FAQObject getFAQObject(@PathVariable("id") Long id) {
        return service.getByID(id);
    }

    @RequestMapping(value = "/FAQObject", method = RequestMethod.POST)
    @ResponseBody
    public FAQObject saveFAQObject(@RequestBody FAQObject faqObject) {
        return service.save(faqObject);
    }

    @RequestMapping(value = "/FAQObject/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/FAQObject/search", method = RequestMethod.POST)
    @ResponseBody
    public List<FAQObject> findByQuestion(@RequestBody StringIn question){
        return service.findByQuestion(question.stringIn);
    }
}
