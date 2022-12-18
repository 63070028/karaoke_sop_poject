package com.example.paymentservice.controller.event;




import com.example.paymentservice.pojo.Payment;
import com.example.paymentservice.service.PaymentsService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventsHandler {
    private final PaymentsService paymentsService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        System.out.println("P_id: "+event.get_id());
        System.out.println("ChangeStatusPayment: "+event.getPayment_status());
        Payment payment = new Payment();
        BeanUtils.copyProperties(event, payment);
        Payment paymentFind = paymentsService.findByPaymentId(event.get_id());
        if(paymentFind != null)
            paymentsService.updatePayment(payment);

        if(event.getPayment_status().equals("complete")){
            System.out.println("rabbitSendOrder:"+event.getOrderId());
            rabbitTemplate.convertAndSend("MyPaymentDirectExchange", "orderstatus", event.getOrderId());
        }

    }

    @EventHandler
    public void del(DelPaymentEvent event){
        paymentsService.delPayment(event.get_id());
    }





}
