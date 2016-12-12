package com.profCom.service.Question;

import com.profCom.entity.Question;
import com.profCom.entity.User;
import com.profCom.repository.QuestionRepository;
import com.profCom.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private UserService Uservice;

    public List<Question> getAll() {
        return repository.findAll();
    }

    public Question getByID(long id) {
        return repository.findOne(id);
    }

    public Question save(Question question) {
        return repository.saveAndFlush(question);
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public List<Question> findByQuestion(String question) {
        return repository.findByQuestion(question);
    }

    public List<Question> findByQuestioner(Long Uid) {
        return repository.findByQuestioner(Uservice.getByID(Uid));
    }

    public List<Question> findByAnswerer(User user) {
        return repository.findByAnswerer(user);
    }
}
