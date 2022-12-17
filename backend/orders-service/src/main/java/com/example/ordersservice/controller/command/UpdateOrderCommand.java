package com.example.ordersservice.controller.command;

import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.roomservice.pojo.Room;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateOrderCommand {

    @TargetAggregateIdentifier
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
}
