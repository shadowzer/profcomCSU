package com.profCom.service.News;

import com.profCom.entity.News;
import com.profCom.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository repository;

    public List<News> getAll() {
        return repository.findAll();
    }

    public News getByID(long id) {
        return repository.findOne(id);
    }

    public News save(News news) {
        return repository.saveAndFlush(news);
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public List<News> getNewsFromTo(long from, long to) {
        List<News> listN=new ArrayList<News>();
        News n=new News();
        n.message="123";
        n.title="123";
        n=save(n);
        long max =n.getId();
        remove(max);
        for (Long i=from;i<to;i++)
        {
            n=repository.findOne(max-i);
            if (n!=null)
            listN.add(n);
            if ((max-i)<1)
            {
                return listN;
            }
        }
        return listN;
    }

    public Long getMaxId() {
        News n=new News();
        n.message="123";
        n.title="123";
        n=save(n);
        long max =n.getId();
        remove(max);
        while(max<0)
        {
            n=getByID(max);
            if (n!=null)
            {
                return n.getId();
            }
        }
        return null;
    }
}
