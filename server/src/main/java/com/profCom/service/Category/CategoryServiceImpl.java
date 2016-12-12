package com.profCom.service.Category;

import com.profCom.entity.Category;
import com.profCom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getByID(Integer id) {
        return repository.findOne(id);
    }

    public Category save(Category category) {
        return repository.saveAndFlush(category);
    }

    public void remove(Integer id) {
        repository.delete(id);
    }
}
