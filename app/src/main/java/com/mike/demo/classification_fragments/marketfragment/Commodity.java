package com.mike.demo.classification_fragments.marketfragment;

public class Commodity {
    int imageId;

    public Commodity(int imageId, String name, float price) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    String name;
    float price;
}
