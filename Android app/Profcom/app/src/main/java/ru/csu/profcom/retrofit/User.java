package ru.csu.profcom.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("surName")
    @Expose
    private String surName;
    @SerializedName("studentGroup")
    @Expose
    private String studentGroup;
    @SerializedName("budget")
    @Expose
    private Boolean budget;
    @SerializedName("fulltime")
    @Expose
    private Boolean fulltime;
    @SerializedName("tabNum")
    @Expose
    private String tabNum;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("feePay")
    @Expose
    private Boolean feePay;
    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Boolean getBudget() {
        return budget;
    }

    public void setBudget(Boolean budget) {
        this.budget = budget;
    }

    public Boolean getFulltime() {
        return fulltime;
    }

    public void setFulltime(Boolean fulltime) {
        this.fulltime = fulltime;
    }

    public String getTabNum() {
        return tabNum;
    }

    public void setTabNum(String tabNum) {
        this.tabNum = tabNum;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Boolean getFeePay() {
        return feePay;
    }

    public void setFeePay(Boolean feePay) {
        this.feePay = feePay;
    }

}