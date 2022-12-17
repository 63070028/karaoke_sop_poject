package com.example.paymentservice.controller.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdatePaymentCommand {

    @TargetAggregateIdentifier
    private String _id;
    private String orderId;
    private String payment_date;
    private String payment_time;
    private int amount_paid;
    private String email;
    private String phone;
    private String payment_status;
}
