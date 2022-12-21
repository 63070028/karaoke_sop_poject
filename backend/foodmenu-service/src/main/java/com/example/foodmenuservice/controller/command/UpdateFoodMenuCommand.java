package com.example.foodmenuservice.controller.command;


import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@Builder
public class UpdateFoodMenuCommand {

    @TargetAggregateIdentifier
    private String _id;
    private String name;
    private String type;
    private List<String> foods;
    private int price;
    private int quantity;
    private String img;
}
