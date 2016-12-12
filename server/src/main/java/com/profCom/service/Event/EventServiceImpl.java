package com.profCom.service.Event;

import com.profCom.entity.Event;
import com.profCom.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Event getByID(long id) {
        return repository.findOne(id);
    }

    public Event save(Event event) {
        return repository.saveAndFlush(event);
    }

    public void remove(long id) {
        repository.delete(id);
    }
}
