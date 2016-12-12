package com.profCom.service.Gift;

import com.profCom.entity.Gift;
import com.profCom.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftRepository repository;

    public List<Gift> getAll() {
        return repository.findAll();
    }

    public Gift getByID(long id) {
        return repository.findOne(id);
    }

    public Gift save(Gift Gift) {
        if (repository.findByName(Gift.getName()).isEmpty())
            return repository.saveAndFlush(Gift);
        Gift gift=null;
        for(Gift a:repository.findByName(Gift.getName()))
            {
                gift=a;
            }
        return gift;
    }

    public void remove(long id) {
        repository.delete(id);
    }
}
