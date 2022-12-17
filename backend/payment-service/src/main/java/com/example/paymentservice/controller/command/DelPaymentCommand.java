package com.example.paymentservice.controller.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DelPaymentCommand {
    @TargetAggregateIdentifier
    private String _id;

}
