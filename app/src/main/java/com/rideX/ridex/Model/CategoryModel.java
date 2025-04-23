package com.rideX.ridex.Model;

public class CategoryModel {
    private final int id;
    private final String picName;
    private final String title;

    public CategoryModel(int id, String picName, String title) {
        this.id = id;
        this.picName = picName;
        this.title = title;
    }

    public int getId() { return id; }
    public String getPicName() { return picName; }
    public String getTitle() { return title; }
}
