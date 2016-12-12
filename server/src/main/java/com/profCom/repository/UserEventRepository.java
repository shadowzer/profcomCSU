package com.profCom.repository;

import com.profCom.entity.Event;
import com.profCom.entity.User;
import com.profCom.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 11.12.2016.
 */
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
    public List<UserEvent> findByUser(User Uid);
    public List<UserEvent> findByUserAndEvent(User Uid, Event event);
    public List<UserEvent> findByEvent(Event event);
}
