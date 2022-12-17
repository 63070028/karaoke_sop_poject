package com.example.paymentservice.controller.event;

import lombok.Data;


@Data
public class UpdatePaymentEvent {
    private String _id;
    private String orderId;
    private String payment_date;
    private String payment_time;
    private int amount_paid;
    private String email;
    private String phone;
    private String payment_status;
}
