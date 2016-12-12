package com.profCom.repository;

import com.profCom.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 09.12.2016.
 */
public interface GiftRepository extends JpaRepository<Gift,Long> {
    public List<Gift> findByName(String name);
}
