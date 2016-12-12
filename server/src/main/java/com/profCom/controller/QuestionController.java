package com.profCom.controller;

import com.profCom.entity.Question;
import com.profCom.entity.User;
import com.profCom.service.Question.QuestionService;
import com.profCom.sysLogic.StringIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by VolgiNN on 12.12.2016.
 */
@RestController
public class QuestionController {
    @Autowired
    private QuestionService service;

    @RequestMapping(value = "/Question", method = RequestMethod.GET)
    @ResponseBody
    public List<Question> getAllQuestions() {
        List<Question> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/Question/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Question getQuestion(@PathVariable("id") long QuestionID) {
        return service.getByID(QuestionID);
    }

    @RequestMapping(value = "/Question", method = RequestMethod.POST)
    @ResponseBody
    public Question saveQuestion(@RequestBody Question question) {
        return service.save(question);
    }

    @RequestMapping(value = "/Question/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/User/{id}/Question", method = RequestMethod.GET)
    @ResponseBody
    public List<Question> getAllUserQuestions(@PathVariable("id") Long id){
        return service.findByQuestioner(id);
    }

    @RequestMapping(value = "/Question/findByQuestion", method = RequestMethod.POST)
    @ResponseBody
    public List<Question> findByQuestion(@RequestBody StringIn stringIn){
        return service.findByQuestion(stringIn.stringIn);
    }

    @RequestMapping(value = "/User/findWhereAnswererNull", method = RequestMethod.POST)
    @ResponseBody
    public List<Question> findWhereAnswererNull(){
        return service.findByAnswerer(null);
    }
}