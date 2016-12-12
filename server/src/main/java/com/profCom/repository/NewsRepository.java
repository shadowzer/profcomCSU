package com.profCom.repository;

import com.profCom.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by VolgiNN on 11.12.2016.
 */
public interface NewsRepository extends JpaRepository<News,Long> {
}
