package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by VolgiNN on 11.12.2016.
 */
@Entity
@Table(name="NewsCategory")
public class NewsCategory  {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @OneToOne
    @JoinColumn(name="Nid",nullable = false)
    public News news;

    @OneToOne
    @JoinColumn(name="Cid",nullable = false)
    public Category category;

    public NewsCategory() {
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public News getNews() {
        return news;
    }
    public void setNews(News news) {
        this.news = news;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
