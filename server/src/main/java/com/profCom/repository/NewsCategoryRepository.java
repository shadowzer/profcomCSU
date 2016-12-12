package com.profCom.repository;

import com.profCom.entity.Category;
import com.profCom.entity.News;
import com.profCom.entity.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 11.12.2016.
 */
public interface NewsCategoryRepository extends JpaRepository<NewsCategory,Long> {
    public List<NewsCategory> findByNews(News Nid);
    public List<NewsCategory> findByNewsAndCategory(News Nid, Category category);
    public List<NewsCategory> findByCategory(Category Cid);
}
