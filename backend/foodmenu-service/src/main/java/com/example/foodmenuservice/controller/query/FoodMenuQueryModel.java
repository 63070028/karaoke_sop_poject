package com.example.foodmenuservice.controller.query;

import lombok.Data;

import java.util.List;

@Data
public class FoodMenuQueryModel {
    private String _id;
    private String name;
    private String type;
    private List<String> foods;
    private int price;
    private int quantity;
    private String img;
}
