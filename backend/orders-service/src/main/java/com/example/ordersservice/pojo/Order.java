package com.example.ordersservice.pojo;



import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
//@Entity
//@Table(name = "orders")
@Data
@Document("Order")
public class Order implements Serializable {


    @Id
    private String _id;
    private String name;
    private String email;
    private String reserve_date;
    private String time;
    private int result;
    private String status;

    private String roomId;
    private String roomName;
    private String accessoryName;
    private int microphone;

    private String foodMenuSetId;
    private String foodMenuName;

    private String drinkMenuSetId;
    private  String drinkMenuName;



    public Order() {
    }

    public Order(String _id, String name, String email, String reserve_date, String time, int result, String status, String roomId, String roomName, String accessoryName, int microphone, String foodMenuSetId, String foodMenuName, String drinkMenuSetId, String drinkMenuName) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.reserve_date = reserve_date;
        this.time = time;
        this.result = result;
        this.status = status;
        this.roomId = roomId;
        this.roomName = roomName;
        this.accessoryName = accessoryName;
        this.microphone = microphone;
        this.foodMenuSetId = foodMenuSetId;
        this.foodMenuName = foodMenuName;
        this.drinkMenuSetId = drinkMenuSetId;
        this.drinkMenuName = drinkMenuName;
    }
}
