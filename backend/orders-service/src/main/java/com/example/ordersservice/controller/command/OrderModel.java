package com.example.ordersservice.controller.command;


import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.roomservice.pojo.Room;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderModel{

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

}
