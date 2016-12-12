package com.profCom.service.UserCategory;

import com.profCom.entity.Category;
import com.profCom.entity.User;
import com.profCom.entity.UserCategory;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface UserCategoryService {
    List<UserCategory> getAll();
    UserCategory getByID(long id);
    UserCategory save(UserCategory userCategory) throws Exception;
    void remove(long id);
    List<UserCategory> findByUser(User u);
    List<UserCategory> findByUserAndCategory(User user, Category category);
    List<Category> getUserCategories(Long Uid);
}