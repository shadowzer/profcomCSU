package com.profCom.controller;

import com.profCom.entity.News;
import com.profCom.service.News.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 11.12.2016.
 */
@RestController
public class NewsController {
    @Autowired
    private NewsService service;

    @RequestMapping(value = "/News", method = RequestMethod.GET)
    @ResponseBody
    public List<News> getAllNews() {
        return service.getAll();
    }

    @RequestMapping(value = "/News/{id}", method = RequestMethod.GET)
    @ResponseBody
    public News getNews(@PathVariable("id") long NewsID) {
        return service.getByID(NewsID);
    }

    @RequestMapping(value = "/News", method = RequestMethod.POST)
    @ResponseBody
    public News saveNews(@RequestBody News news) {
        return service.save(news);
    }

    @RequestMapping(value = "/News/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/News/{from}/{to}", method = RequestMethod.GET)
    @ResponseBody
    public void getNewsFromTo(@PathVariable("from") long from,@PathVariable("to") long to) {
        service.getNewsFromTo(from,to);
    }

    @RequestMapping(value = "/News/MaxId", method = RequestMethod.GET)
    @ResponseBody
    public Long getMAxId() {
        return service.getMaxId();
    }

}
