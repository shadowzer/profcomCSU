package com.profCom.repository;

import com.profCom.entity.FAQObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface FAQObjectRepository extends JpaRepository<FAQObject,Long> {
    public List<FAQObject> findByQuestion(String question);
}
