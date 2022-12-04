package com.sdv.FoodService.Models.Request;

import com.sdv.FoodService.Models.Food;

import javax.validation.constraints.NotNull;

public class AddFoodRequestModel {

    private String image;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotNull(message = "Weight cannot be null")
    private Double weight;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Food ToFood(){
        return new Food(image,title,price,weight);
    }
}
