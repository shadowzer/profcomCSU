package com.profCom.service.News;

import com.profCom.entity.News;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface NewsService {
    List<News> getAll();
    News getByID(long id);
    News save(News news);
    void remove(long id);
    List<News> getNewsFromTo(long from,long to);
    Long getMaxId();
}