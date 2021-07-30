package com.mike.demo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User2 {
    @Expose
    int id;

    @Expose
    String userName;

    @Expose(serialize = true, deserialize = false)
    String userPassword;

    @Expose
    String sex;

    public User2(int id, String userName, String userPassword, String sex) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

