package com.rideX.ridex.Model;

public class CategoryModel {
    private int id;
    private String picName, title;

    public CategoryModel(int id, String picName, String title) {
        this.id = id;
        this.picName = picName;
        this.title = title;
    }

    public int getId() { return id; }
    public String getPicName() { return picName; }
    public String getTitle() { return title; }
}
