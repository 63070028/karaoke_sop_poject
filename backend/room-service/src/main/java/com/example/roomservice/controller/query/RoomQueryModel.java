package com.example.roomservice.controller.query;

import lombok.Data;

@Data
public class RoomQueryModel {
    private String _id;
    private String name;
    private String accessory;
    private int price;
    private String detail;
    private String img;
}
