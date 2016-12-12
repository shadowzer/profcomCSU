package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VolgiNN on 10.12.2016.
 */
@Entity
@Table(name="UserCategory")
public class UserCategory  {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @OneToOne
    @JoinColumn(name="Uid",nullable = false)
    public User user;

    @OneToOne
    @JoinColumn(name="Cid",nullable = false)
    public Category category;

    public UserCategory() {
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
