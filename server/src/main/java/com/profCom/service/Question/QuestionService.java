package com.profCom.service.Question;

import com.profCom.entity.Question;
import com.profCom.entity.User;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface QuestionService {
    List<Question> getAll();
    Question getByID(long id);
    Question save(Question question);
    void remove(long id);
    List<Question> findByQuestion(String question);
    List<Question> findByQuestioner(Long Uid);
    List<Question> findByAnswerer(User user);
}