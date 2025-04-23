package com.rideX.ridex.Model;

public class CarModel {
    private String title, picName, ContactBuyer, MilageRan, TotalCapacity, description;
    private int price;
    private double rating;

    // Constructor
    public CarModel(String title, String picName, String ContactBuyer, String MilageRan,
                    int price, double rating, String totalCapacity, String description) {
        this.title = title;
        this.picName = picName;
        this.ContactBuyer = ContactBuyer;
        this.MilageRan = MilageRan ;
        this.price = price;
        this.rating = rating;
        this.TotalCapacity = totalCapacity;
        this.description = description;
    }

    // Getters
    public String getTitle() { return title; }
    public String getPicName() { return picName; }
    public String getContactBuyer() { return ContactBuyer; }
    public String getMilageRan() { return MilageRan; }
    public int getPrice() { return price; }
    public double getRating() { return rating; }
    public String getTotalCapacity() { return TotalCapacity; }
    public String getDescription() { return description; }
}

