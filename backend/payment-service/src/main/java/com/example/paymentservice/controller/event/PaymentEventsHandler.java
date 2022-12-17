package com.example.paymentservice.controller.event;




import com.example.paymentservice.pojo.Payment;
import com.example.paymentservice.service.PaymentsService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventsHandler {
    private final PaymentsService paymentsService;

    public PaymentEventsHandler(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @EventHandler
    public void add(CreatePaymentEvent event){
        Payment payment = new Payment();
        BeanUtils.copyProperties(event, payment);
        paymentsService.addPayment(payment);
    }

    @EventHandler
    public void update(UpdatePaymentEvent event){
        Payment payment = new Payment();
        BeanUtils.copyProperties(event, payment);
        Payment paymentFind = paymentsService.findByPaymentId(event.get_id());
        if(paymentFind != null)
            paymentsService.updatePayment(payment);
    }

    @EventHandler
    public void del(DelPaymentEvent event){
        paymentsService.delPayment(event.get_id());
    }





}
