package com.profCom.service.UserEvent;

import com.profCom.entity.Event;
import com.profCom.entity.User;
import com.profCom.entity.UserEvent;
import com.profCom.repository.UserEventRepository;
import com.profCom.service.Event.EventService;
import com.profCom.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class UserEventServiceImpl implements UserEventService {

    @Autowired
    private UserEventRepository repository;

    @Autowired
    private UserService Uservice;

    @Autowired
    private EventService Eservice;

    public List<UserEvent> getAll() {
        return repository.findAll();
    }

    public UserEvent getByID(long id) {
        return repository.findOne(id);
    }

    public UserEvent save(UserEvent userEvent) throws Exception {
        if (!searchByUidAndCid(userEvent.getUser().getId(),userEvent.getEvent().getId()))
        {
            return repository.saveAndFlush(userEvent);
        }
        throw new Exception("this combination user/UserEvent is already exist");
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public List<UserEvent> findByUser(User u){
        return repository.findByUser(u);
    }

    public List<UserEvent> findByUserAndEvent(User user, Event event){
        return repository.findByUserAndEvent(user,event);
    }

    public List<UserEvent> findByEvent(Event event){
        return repository.findByEvent(event);
    }

    private boolean searchByUidAndCid(Long Uid,Long Eid){
        List<UserEvent> userEvent=repository.findByUserAndEvent(Uservice.getByID(new Long(Uid)),Eservice.getByID(Eid));
        if (userEvent.isEmpty())
        {
            return false;
        }
        return true;
    }

    public List<Event> getEventsByUid(Long Uid){
        List<UserEvent> listUE=repository.findByUser(Uservice.getByID(new Long(Uid)));
        List<Event> listE=new ArrayList<Event>();
        for(UserEvent UE:listUE)
        {
            listE.add(UE.getEvent());
        }
        return listE;
    }

    public List<User> getUsersByEventId( Long Eid) {
        List<UserEvent> listUE = repository.findByEvent(Eservice.getByID(Eid));
        List<User> listU=new ArrayList<User>();
        for(UserEvent UE:listUE){
            listU.add(UE.getUser());
        }
        return listU;
    }
}
