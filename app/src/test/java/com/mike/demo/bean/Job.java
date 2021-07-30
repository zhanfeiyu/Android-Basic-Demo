package com.mike.demo.bean;

public class Job {
    public Job(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    String name;
    int salary;
}
