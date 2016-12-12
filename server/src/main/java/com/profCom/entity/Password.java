package com.profCom.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Entity
@Table(name="Password")
public class Password{

    @Id
    @Column(name="login",nullable = false)
    public String login;

    @Column(name="password",nullable = false)
    public String password;

    public Password() {
    }

    @OneToOne
    @JoinColumn(name="id",nullable = false)
    public User user;

    public String getLogin() {

        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
