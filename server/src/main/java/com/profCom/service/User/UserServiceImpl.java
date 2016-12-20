package com.profCom.service.User;

import com.profCom.entity.User;
import com.profCom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getByID(long id) {
        return repository.findOne(id);
    }

    public User save(User user) {
        return repository.saveAndFlush(user);
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public long autorizate(String login,String password){
        List<User> user = repository.findByLogin(login);
        if (user.isEmpty())
            return -2;
        User u=user.remove(0);
        if (password.equals(u.getPassword()))
            return u.getId();
        return -1;
    }

    public User findByLogin(String login) {
        for (User u:repository.findByLogin(login)){
            return u;
        }
        return null;
    }
}
