package com.profCom.service.FAQObject;

import com.profCom.entity.FAQObject;
import com.profCom.entity.User;
import com.profCom.repository.FAQObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class FAQObjectServiceImpl implements FAQObjectService {

    @Autowired
    private FAQObjectRepository repository;

    public List<FAQObject> getAll() {
        return repository.findAll();
    }

    public FAQObject getByID(long id) {
        return repository.findOne(id);
    }

    public FAQObject save(FAQObject faqObject) {
        return repository.saveAndFlush(faqObject);
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public List<FAQObject> findByQuestion(String question) {
        return repository.findByQuestion(question);
    }
}
