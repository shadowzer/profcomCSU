package com.profCom.service.UserGift;

import com.profCom.entity.UserGift;
import com.profCom.repository.UserGiftRepository;
import com.profCom.service.Gift.GiftService;
import com.profCom.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class UserGiftServiceImpl implements UserGiftService {

    @Autowired
    private UserGiftRepository repository;

    @Autowired
    private UserService Uservice;

    @Autowired
    private GiftService Gservice;

    public List<UserGift> getAll() {
        return repository.findAll();
    }

    public UserGift getByID(long id) {
        return repository.findOne(id);
    }

    public UserGift save(UserGift userGift) {
        userGift.date=new Date();
        return repository.saveAndFlush(userGift);
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public List<UserGift> findByUser(Long Uid) {
        return repository.findByUser(Uservice.getByID(Uid));
    }

    public List<UserGift> findByGift(Long Gid) {
        return repository.findByGift(Gservice.getByID(Gid));
    }
}
