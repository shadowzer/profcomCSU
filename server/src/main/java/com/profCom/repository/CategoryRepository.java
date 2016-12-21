package com.profCom.repository;

import com.profCom.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 09.12.2016.
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByName(String name);
}
