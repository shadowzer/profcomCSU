package com.profCom.repository;

import com.profCom.entity.Category;
import com.profCom.entity.User;
import com.profCom.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 10.12.2016.
 */
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    public List<UserCategory> findByUser(User Uid);
    public List<UserCategory> findByUserAndCategory(User Uid, Category category);
}
