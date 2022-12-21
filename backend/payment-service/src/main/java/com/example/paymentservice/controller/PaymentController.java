package com.example.paymentservice.controller;


import com.example.paymentservice.controller.command.CreatePaymentCommand;
import com.example.paymentservice.controller.command.DelPaymentCommand;
import com.example.paymentservice.controller.command.Model.PaymentModel;
import com.example.paymentservice.controller.command.UpdatePaymentCommand;
import com.example.paymentservice.controller.query.FindPaymentsQuery;
import com.example.paymentservice.controller.query.PaymentQueryModel;
import com.example.paymentservice.pojo.Payment;
import com.example.paymentservice.service.PaymentsService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentController {


    private final CommandGateway commandGateway;
    private  final QueryGateway queryGateway;

    private PaymentsService paymentsService;

    @Autowired
    public PaymentController(CommandGateway commandGateway, QueryGateway queryGateway, PaymentsService paymentsService) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.paymentsService = paymentsService;
    }




    @RequestMapping(value = "/getPayments", method = RequestMethod.GET)
    public List<PaymentQueryModel> getPayments(){
        FindPaymentsQuery findPaymentsQuery = new FindPaymentsQuery();
        List<PaymentQueryModel> payments = queryGateway.query(findPaymentsQuery, ResponseTypes.multipleInstancesOf(PaymentQueryModel.class)).join();
        return payments;
    }



    @RequestMapping(value = "/addPayment", method = RequestMethod.POST)
    public String addPayment(@RequestBody PaymentModel payment) {
        System.out.println("Add Payment OrderId: "+payment.getOrderId());
        CreatePaymentCommand command = CreatePaymentCommand.builder()
                ._id(UUID.randomUUID().toString())
                .orderId(payment.getOrderId())
                .payment_date(payment.getPayment_date())
                .payment_time(payment.getPayment_time())
                .amount_paid(payment.getAmount_paid())
                .email(payment.getEmail())
                .phone(payment.getPhone())
                .payment_status(payment.getPayment_status())
                .build();

        String result;
        try {
            commandGateway.sendAndWait(command);
            result = "addPayment Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }


    @RequestMapping(value="/updateStatusPayment", method = RequestMethod.POST)
    public String updatePayment(@RequestParam("paymentId") String paymentId, @RequestParam("status") String status){
        System.out.println("Update Status OrderId : "+paymentId);
        Payment payment = paymentsService.findByPaymentId(paymentId);
        UpdatePaymentCommand command = UpdatePaymentCommand.builder()
                ._id(paymentId)
                .orderId(payment.getOrderId())
                .payment_date(payment.getPayment_date())
                .payment_time(payment.getPayment_time())
                .amount_paid(payment.getAmount_paid())
                .email(payment.getEmail())
                .phone(payment.getPhone())
                .payment_status(status)
                .build();
        String result;
        try {
             commandGateway.sendAndWait(command);
             result = "UpdatePayment Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }

    @RequestMapping(value="/delPayment", method = RequestMethod.DELETE)
    public String deletePayment(@RequestParam("_id") String _id) {
        System.out.println("Del Payment: "+_id);
        DelPaymentCommand command = DelPaymentCommand.builder()
                ._id(_id)
                .build();
        String result;
        try {
            commandGateway.sendAndWait(command);
            result = "DelPayment Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }






}
