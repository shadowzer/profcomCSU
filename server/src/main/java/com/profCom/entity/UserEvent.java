package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by VolgiNN on 11.12.2016.
 */
@Entity
@Table(name="UserEvent")
public class UserEvent  {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @OneToOne
    @JoinColumn(name="Uid",nullable = false)
    public User user;

    @OneToOne
    @JoinColumn(name="Eid",nullable = false)
    public Event event;

    public UserEvent() {
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
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event Event) {
        this.event = Event;
    }
}