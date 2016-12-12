package com.profCom.service.NewsCategory;

import com.profCom.entity.Category;
import com.profCom.entity.News;
import com.profCom.entity.NewsCategory;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface NewsCategoryService {
    List<NewsCategory> getAll();
    NewsCategory getByID(long id);
    NewsCategory save(NewsCategory newsCategory) throws Exception;
    void remove(long id);
    List<NewsCategory> findByNews(News news);
    List<NewsCategory> findByNewsAndCategory(News news, Category category);
    List<NewsCategory> findByCategory(Category category);
}