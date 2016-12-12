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
    public String feePay;

    @Column(name="avatar")
    public byte[] avatar;

    @Column(name="budget", nullable = false, length=10)
    public String budget;

    @Column(name="fulltime", nullable = false, length=10)
    public String fulltime;

    @Column(name="tabNum", length=50)
    public String tabNum;



    public User() {
    }
    public String getTabNum() {
        return tabNum;
    }
    public void setTabNum(String tabNum) {
        this.tabNum = tabNum;
    }
    public String getBudget() {

        return budget;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }
    public String getFulltime() {
        return fulltime;
    }
    public void setFulltime(String fulltime) {
        this.fulltime = fulltime;
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
    public String getFeePay() {
        return feePay;
    }
    public void setFeePay(String feePay) {
        this.feePay = feePay;
    }
    public byte[] getAvatar() {
        return avatar;
    }
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
