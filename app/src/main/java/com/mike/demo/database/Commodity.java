package com.mike.demo.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//定义Commodity表
@Entity
public class Commodity {
    @PrimaryKey (autoGenerate = true)
    private int id;

    int imageId;
    String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    float price;

    public Commodity(int imageId, String name, float price) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
