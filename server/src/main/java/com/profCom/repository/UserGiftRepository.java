package com.profCom.repository;

import com.profCom.entity.Gift;
import com.profCom.entity.User;
import com.profCom.entity.UserGift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 12.12.2016.
 */
public interface UserGiftRepository extends JpaRepository<UserGift,Long> {
    public List<UserGift> findByUser(User u);
    public List<UserGift> findByGift(Gift g);
}
