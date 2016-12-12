package com.profCom.service.Event;

import com.profCom.entity.Event;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface EventService {
    List<Event> getAll();
    Event getByID(long id);
    Event save(Event event);
    void remove(long id);
}