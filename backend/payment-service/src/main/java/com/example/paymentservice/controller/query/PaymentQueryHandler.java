package com.example.paymentservice.controller.query;


import com.example.paymentservice.pojo.Payment;
import com.example.paymentservice.service.PaymentsService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentQueryHandler {
    private PaymentsService paymentsService;

    public PaymentQueryHandler(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @QueryHandler
    List<PaymentQueryModel> findPayments(FindPaymentsQuery query){
        List<PaymentQueryModel> payments = new ArrayList<>();
        List<Payment> storedPayments = paymentsService.showPayments();
        for (Payment payment: storedPayments) {
            PaymentQueryModel paymentQueryModel = new PaymentQueryModel();
            BeanUtils.copyProperties(payment, paymentQueryModel);
            payments.add(paymentQueryModel);
        }
        return  payments;
    }
}
