package com.mike.demo.bean;

public class User {
    String name;
    int age;
    boolean isStudent;

    public User(String name, int age, boolean isStudent) {
        this.name = name;
        this.age = age;
        this.isStudent = isStudent;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", job=" + job +
                '}';
    }

    public boolean isStudent() {
        return isStudent;
    }

    Job job;
}
