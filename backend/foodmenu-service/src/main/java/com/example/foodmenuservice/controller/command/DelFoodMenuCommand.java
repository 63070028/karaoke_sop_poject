package com.example.foodmenuservice.controller.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DelFoodMenuCommand {
    @TargetAggregateIdentifier
    private String _id;
}
