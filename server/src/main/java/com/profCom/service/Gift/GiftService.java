package com.profCom.service.Gift;

import com.profCom.entity.Gift;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface GiftService {
    List<Gift> getAll();
    Gift getByID(long id);
    Gift save(Gift Gift);
    void remove(long id);
}