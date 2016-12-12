package com.profCom.service.Password;

import com.profCom.entity.Password;
import com.profCom.repository.PasswordRepository;
import com.profCom.service.Password.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private PasswordRepository repository;

    public List<Password> getAll() {
        return repository.findAll();
    }

    public Password getByID(String login) {
        return repository.findOne(login);
    }

    public Password save(Password Password) {
        return repository.saveAndFlush(Password);
    }

    public void remove(String login) {
        repository.delete(login);
    }

    public long autorizate(String login,String password){
        Password pass = repository.findOne(login);
        if (pass == null)
            return -2;
        if (password.equals(pass.password))
            return pass.user.id;
        return -1;
    }
}
