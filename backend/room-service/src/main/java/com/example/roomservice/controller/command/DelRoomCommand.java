package com.example.roomservice.controller.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DelRoomCommand {
    @TargetAggregateIdentifier
    private String _id;

}
