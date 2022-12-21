package com.example.roomservice.controller.command.Model;

import lombok.Data;

@Data
public class RoomUpdateModel {
    private String _id;
    private String name;
    private String accessory;
    private int price;
    private String detail;
    private String img;
}
