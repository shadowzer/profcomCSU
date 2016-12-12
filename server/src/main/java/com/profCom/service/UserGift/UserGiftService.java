package com.profCom.service.UserGift;

import com.profCom.entity.Gift;
import com.profCom.entity.User;
import com.profCom.entity.UserGift;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface UserGiftService {
    List<UserGift> getAll();
    UserGift getByID(long id);
    UserGift save(UserGift userGift);
    void remove(long id);
    List<UserGift> findByUser(Long Uid);
    List<UserGift> findByGift(Long Gid);
}