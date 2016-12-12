package com.profCom.service.Category;

import com.profCom.entity.Category;
import com.profCom.entity.User;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface CategoryService {
    List<Category> getAll();
    Category getByID(Integer id);
    Category save(Category category);
    void remove(Integer id);
}