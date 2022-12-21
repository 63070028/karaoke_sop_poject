package com.example.ordersservice.pojo;



import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.roomservice.pojo.Room;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
//@Entity
//@Table(name = "orders")
@Data
@Document("Orders")
public class Order implements Serializable {


    @Id
    private String _id;
    private String name;
    private String email;
    private String phone;

    private String reserve_date;
    private String time;

    private Room room;
    private int microphone;
    private FoodMenu foodMenu;
    private FoodMenu drinkMenu;

    private int result;
    private String status;


    public Order() {
    }

    public Order(String _id, String name, String email, String phone, String reserve_date, String time, Room room, int microphone, FoodMenu foodMenu, FoodMenu drinkMenu, int result, String status) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.reserve_date = reserve_date;
        this.time = time;
        this.room = room;
        this.microphone = microphone;
        this.foodMenu = foodMenu;
        this.drinkMenu = drinkMenu;
        this.result = result;
        this.status = status;
    }
}
