package com.rideX.ridex.Model;

public class CarModel {
    private String title, picName, contactBuyer, milageRan, totalCapacity, description;
    private int price;
    private double rating;

    // Constructor
    public CarModel(String title, String picName, String contactBuyer, String milageRan,
                    int price, double rating, String totalCapacity, String description) {
        this.title = title;
        this.picName = picName;
        this.contactBuyer = contactBuyer;
        this.milageRan = milageRan ;
        this.price = price;
        this.rating = rating;
        this.totalCapacity = totalCapacity;
        this.description = description;
    }

    // Getters
    public String getTitle() { return title; }
    public String getPicName() { return picName; }
    public String getContactBuyer() { return contactBuyer; }
    public String getMilageRan() { return milageRan; }
    public int getPrice() { return price; }
    public double getRating() { return rating; }
    public String getTotalCapacity() { return totalCapacity; }
    public String getDescription() { return description; }
}

