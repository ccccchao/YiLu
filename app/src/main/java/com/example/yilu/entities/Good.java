package com.example.yilu.entities;

public class Good {
    private Integer id;
    private String name, category;
    private double price;
    private Integer amount;
    private Integer image;
    private boolean is_checked;

    public Good(Integer id, String name, String category, double price, Integer amount, Integer image, boolean is_checked) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.amount = amount;
        this.image = image;
        this.is_checked = is_checked;
    }

    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public boolean getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(boolean is_checked) {
        this.is_checked = is_checked;
    }

}
