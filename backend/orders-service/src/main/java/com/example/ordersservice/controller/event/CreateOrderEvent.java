package com.example.ordersservice.controller.event;



import lombok.Data;

@Data
public class CreateOrderEvent {

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



}
