package com.profCom.service.UserCategory;

import com.profCom.entity.Category;
import com.profCom.entity.User;
import com.profCom.entity.UserCategory;
import com.profCom.repository.UserCategoryRepository;
import com.profCom.service.Category.CategoryService;
import com.profCom.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired
    private UserCategoryRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    public List<UserCategory> getAll() {
        return repository.findAll();
    }

    public UserCategory getByID(long id) {
        return repository.findOne(id);
    }

    public UserCategory save(UserCategory userCategory) throws Exception {
        if (!searchByUidAndCid(userCategory.getUser().getId(),userCategory.getCategory().getId()))
        {
            return repository.saveAndFlush(userCategory);
        }
        throw new Exception("this combination user/category is already exist");
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public List<UserCategory> findByUser(User u){
        return repository.findByUser(u);
    }

    public List<UserCategory> findByUserAndCategory(User user, Category category){
        return repository.findByUserAndCategory(user,category);
    }

    public List<Category> getUserCategories(Long Uid){
        List<UserCategory> listUC=repository.findByUser(userService.getByID(Uid));
        List<Category> listC=new ArrayList<Category>();
        for(UserCategory UC:listUC)
        {
            listC.add(UC.category);
        }
        return listC;
    }

    private boolean searchByUidAndCid( Long Uid,Integer Cid){
        List<UserCategory> userCategory=repository.findByUserAndCategory(userService.getByID(new Long(Uid)),categoryService.getByID(Cid));
        if (userCategory.isEmpty())
        {
            return false;
        }
        return true;
    }
}
