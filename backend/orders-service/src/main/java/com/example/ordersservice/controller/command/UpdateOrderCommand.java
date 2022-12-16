package com.example.ordersservice.controller.command;

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
