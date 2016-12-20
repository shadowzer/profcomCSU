package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by VolgiNN on 08.12.2016.
 */
@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public long id;

    @Column(name="firstName",nullable = false, length = 50)
    public String firstName;

    @Column(name="surName",nullable = false, length = 50)
    public String surName;

    @Column(name="lastName",nullable = false, length = 50)
    public String lastName;

    @Column(name="feePay", nullable = false, length=15)
    public boolean feePay;

    @Column(name="avatar")
    public byte[] avatar;

    @Column(name="budget", nullable = false, length=10)
    public boolean budget;

    @Column(name="fulltime", nullable = false, length=10)
    public boolean fulltime;

    @Column(name="tabNum", length=50)
    public String tabNum;

    @Column(name="login",nullable = false,unique = true)
    public String login;

    @Column(name="password",nullable = false)
    public String password;

    @Column(name="studentGroup",nullable = false, length = 10)
    public String studentGroup;


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

    public boolean isFeePay() {
        return feePay;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public void setFeePay(boolean feePay) {
        this.feePay = feePay;
    }

    public boolean isBudget() {
        return budget;
    }

    public void setBudget(boolean budget) {
        this.budget = budget;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }





    public User() {
    }
    public String getTabNum() {
        return tabNum;
    }
    public void setTabNum(String tabNum) {
        this.tabNum = tabNum;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public byte[] getAvatar() {
        return avatar;
    }
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
