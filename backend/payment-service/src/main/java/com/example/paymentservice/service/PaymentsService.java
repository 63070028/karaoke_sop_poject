package com.example.paymentservice.service;


import com.example.paymentservice.pojo.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> showPayments() {return paymentRepository.findAll();}

    public boolean addPayment(Payment payment){
        try {
            paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updatePayment(Payment payment){
        try {
            paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delPayment(String id) {
        try {
            paymentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Payment findByPaymentId(String id){
        try {
            Payment payment = paymentRepository.findByPaymentId(id);
            return payment;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
