package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by VolgiNN on 11.12.2016.
 */
@Entity
@Table(name="News")
public class News {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @Column(name="message",nullable = false)
    public String message;

    @Column(name="title",nullable = false)
    public String title;

    @Column(name="image",nullable = false)
    public byte[] image;

    @OneToOne
    @JoinColumn(name="UId",nullable = false)
    public User user;

    @Column(name = "date", columnDefinition="DATETIME",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date date;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public News() {

    }
}
