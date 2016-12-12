package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by VolgiNN on 12.12.2016.
 */
@Entity
@Table(name="UserGift")
public class UserGift {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @OneToOne
    @JoinColumn(name="Uid",nullable = false)
    public User user;

    @OneToOne
    @JoinColumn(name="Gid",nullable = false)
    public Gift gift;

    @Column(name = "date", columnDefinition="DATETIME",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date date;

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

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserGift() {

    }
}
