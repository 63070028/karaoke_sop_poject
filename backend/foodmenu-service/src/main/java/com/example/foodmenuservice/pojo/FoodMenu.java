package com.example.foodmenuservice.pojo;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

//@Entity
//@Table(name = "orders")
@Data
@Document("FoodMenu")
public class FoodMenu implements Serializable {


    @Id
    private String _id;
    private String name;
    private String type;
    private List<String> foods;
    private int price;
    private int quantity;




//    private Object details;

    public FoodMenu() {
    }

    public FoodMenu(String _id, String name, String type, List<String> foods, int price, int quantity) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.foods = foods;
        this.price = price;
        this.quantity = quantity;
    }
}
