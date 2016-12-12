package com.profCom.service.User;

import com.profCom.entity.User;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface UserService {
    List<User> getAll();
    User getByID(long id);
    User save(User user);
    void remove(long id);
}