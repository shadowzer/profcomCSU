package com.profCom.service.UserEvent;

import com.profCom.entity.Event;
import com.profCom.entity.User;
import com.profCom.entity.UserEvent;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface UserEventService {
    List<UserEvent> getAll();
    UserEvent getByID(long id);
    UserEvent save(UserEvent userEvent) throws Exception;
    void remove(long id);
    List<UserEvent> findByUser(User u);
    List<UserEvent> findByUserAndEvent(User user, Event event);
    List<UserEvent> findByEvent(Event event);
    List<Event> getEventsByUid(Long Uid);
    List<User> getUsersByEventId( Long Eid);
}