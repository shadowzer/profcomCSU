package com.profCom.service.Documentation;

import com.profCom.entity.Documentation;
import com.profCom.repository.DocumentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class DocumentationServiceImpl implements DocumentationService {

    @Autowired
    private DocumentationRepository repository;

    public List<Documentation> getAll() {
        return repository.findAll();
    }

    public Documentation getByID(String id) {
        return repository.findOne(id);
    }

    public Documentation save(Documentation documentation) {
        return repository.saveAndFlush(documentation);
    }

    public void remove(String id) {
        repository.delete(id);
    }
}
