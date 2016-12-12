package com.profCom.controller;

import com.profCom.entity.Documentation;
import com.profCom.service.Documentation.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 13.12.2016.
 */@RestController
public class DocumentationController {
    @Autowired
    private DocumentationService service;

    @RequestMapping(value = "/Documentation", method = RequestMethod.GET)
    @ResponseBody
    public List<Documentation> getAllDocumentations() {
        List<Documentation> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/Documentation/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Documentation getDocumentation(@PathVariable("id") String title) {
        return service.getByID(title);
    }

    @RequestMapping(value = "/Documentation", method = RequestMethod.POST)
    @ResponseBody
    public Documentation saveDocumentation(@RequestBody Documentation Documentation) {
        return service.save(Documentation);
    }

    @RequestMapping(value = "/Documentation/{title}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("title") String title) {
        service.remove(title);
    }
}
