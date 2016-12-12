package com.profCom.service.Password;

import com.profCom.entity.Password;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface PasswordService {
    List<Password> getAll();
    Password getByID(String login);
    Password save(Password password);
    void remove(String login);
    long autorizate(String login,String password);
}