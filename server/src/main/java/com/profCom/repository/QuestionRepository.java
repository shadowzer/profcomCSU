package com.profCom.repository;

import com.profCom.entity.Question;
import com.profCom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 12.12.2016.
 */
public interface QuestionRepository extends JpaRepository<Question,Long> {
    public List<Question> findByQuestion(String question);
    public List<Question> findByQuestioner(User questioner);
    public List<Question> findByAnswerer(User answerer);
}
