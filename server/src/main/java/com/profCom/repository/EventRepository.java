package com.profCom.repository;

import com.profCom.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by VolgiNN on 11.12.2016.
 */
public interface EventRepository extends JpaRepository<Event,Long> {
}
