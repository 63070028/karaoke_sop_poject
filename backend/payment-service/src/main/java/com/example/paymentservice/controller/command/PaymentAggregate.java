package com.example.paymentservice.controller.command;



import com.example.paymentservice.controller.event.CreatePaymentEvent;
import com.example.paymentservice.controller.event.DelPaymentEvent;
import com.example.paymentservice.controller.event.UpdatePaymentEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class PaymentAggregate {

    @AggregateIdentifier
    private String _id;
    private String orderId;
    private String payment_date;
    private String payment_time;
    private int amount_paid;
    private String email;
    private String phone;
    private String payment_status;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(CreatePaymentCommand command){
        System.out.println("CreatePaymentCommand");
        CreatePaymentEvent event = new CreatePaymentEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(CreatePaymentEvent event){
        this._id = event.get_id();
        this.orderId = event.getOrderId();
        this.payment_date = event.getPayment_date();
        this.payment_time = event.getPayment_time();
        this.amount_paid = event.getAmount_paid();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.payment_status = event.getPayment_status();
    }

    @CommandHandler
    public void handle(UpdatePaymentCommand command){
        System.out.println("UpdatePaymentCommand");
        UpdatePaymentEvent event = new UpdatePaymentEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(UpdatePaymentEvent event){
        this._id = event.get_id();
        this.orderId = event.getOrderId();
        this.payment_date = event.getPayment_date();
        this.payment_time = event.getPayment_time();
        this.amount_paid = event.getAmount_paid();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.payment_status = event.getPayment_status();
    }


    @CommandHandler
    public void delete(DelPaymentCommand command){
        System.out.println("DelPaymentCommand");
        DelPaymentEvent event = new DelPaymentEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
        AggregateLifecycle.markDeleted();
    }




}
