package com.example.paymentservice.controller.query;

import lombok.Data;

@Data
public class PaymentQueryModel {
    private String _id;
    private String orderId;
    private String payment_date;
    private String payment_time;
    private int amount_paid;
    private String email;
    private String phone;
    private String payment_status;
}
