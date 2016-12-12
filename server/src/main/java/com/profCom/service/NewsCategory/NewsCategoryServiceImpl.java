package com.profCom.service.NewsCategory;

import com.profCom.entity.Category;
import com.profCom.entity.News;
import com.profCom.entity.NewsCategory;
import com.profCom.repository.NewsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    @Autowired
    private NewsCategoryRepository repository;

    public List<NewsCategory> getAll() {
        return repository.findAll();
    }

    public NewsCategory getByID(long id) {
        return repository.findOne(id);
    }

    public NewsCategory save(NewsCategory newsCategory) throws Exception {
        if (findByNewsAndCategory(newsCategory.getNews(), newsCategory.getCategory()).isEmpty()) {
            return repository.saveAndFlush(newsCategory);
        }
        throw new Exception("this combination news/category is already exist");
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public List<NewsCategory> findByNews(News Nid) {
        return repository.findByNews(Nid);
    }

    public List<NewsCategory> findByNewsAndCategory(News news, Category category) {
        return repository.findByNewsAndCategory(news, category);
    }

    public List<NewsCategory> findByCategory(Category category) {
        return repository.findByCategory(category);
    }
}
