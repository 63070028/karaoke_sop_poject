package com.example.paymentservice.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "payments")
public class Payment implements Serializable {


    @Id
    @Column(unique = true)
    private String _id;
    private String orderId;
    private String payment_date;
    private String payment_time;
    private int amount_paid;
    private String email;
    private String phone;
    private String payment_status;

    public Payment() {
    }

    public Payment(String _id, String orderId, String payment_date, String payment_time, int amount_paid, String email, String phone, String payment_status) {
        this._id = _id;
        this.orderId = orderId;
        this.payment_date = payment_date;
        this.payment_time = payment_time;
        this.amount_paid = amount_paid;
        this.email = email;
        this.phone = phone;
        this.payment_status = payment_status;
    }
}
