package com.example.bookmymeal;

public class FoodPackage {
    private int id;
    private String name;
    private int price;
    private String description;
    private String package_image;

    public FoodPackage() {
    }

    public FoodPackage(int id, String name, int price, String description, String package_image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.package_image = package_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackage_image() {
        return package_image;
    }

    public void setPackage_image(String package_image) {
        this.package_image = package_image;
    }
}
