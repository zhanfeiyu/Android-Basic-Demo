package com.mike.demo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User1 {
    int id;
    @SerializedName("n")
    String userName;

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public User1(int id, String userName, String userPassword, String sex) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.sex = sex;
    }

    @SerializedName("p")
    String userPassword;

    @SerializedName("s")
    String sex;
}

