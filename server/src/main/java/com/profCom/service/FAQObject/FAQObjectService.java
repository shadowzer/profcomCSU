package com.profCom.service.FAQObject;

import com.profCom.entity.FAQObject;
import com.profCom.entity.User;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface FAQObjectService {
    List<FAQObject> getAll();
    FAQObject getByID(long id);
    FAQObject save(FAQObject faqObject);
    void remove(long id);
    List<FAQObject> findByQuestion(String question);
}