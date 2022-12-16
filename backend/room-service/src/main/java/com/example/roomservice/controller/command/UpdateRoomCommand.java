package com.example.roomservice.controller.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateRoomCommand {

    @TargetAggregateIdentifier
    private String _id;
    private String name;
    private String accessory;
    private int price;
    private String detail;
}
