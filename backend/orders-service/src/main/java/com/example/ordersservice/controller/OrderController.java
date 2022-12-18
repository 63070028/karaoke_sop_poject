package com.example.ordersservice.controller;


import com.example.ordersservice.controller.command.CreateOrderCommand;
import com.example.ordersservice.controller.command.DelOrderCommand;
import com.example.ordersservice.controller.command.OrderModel;
import com.example.ordersservice.controller.command.UpdateOrderCommand;
import com.example.ordersservice.controller.query.FindOrdersQuery;
import com.example.ordersservice.controller.query.OrderQueryModel;
import com.example.ordersservice.pojo.Order;
import com.example.ordersservice.service.MessageService;
import com.example.ordersservice.service.OrdersService;
import com.example.ordersservice.service.SendEmailService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {


    private final CommandGateway commandGateway;
    private  final QueryGateway queryGateway;

    private OrdersService ordersService;

    private MessageService messageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    public OrderController(CommandGateway commandGateway, QueryGateway queryGateway, OrdersService ordersService, MessageService messageService) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.ordersService = ordersService;
        this.messageService = messageService;
    }

    @RequestMapping(value = "/getOrders", method = RequestMethod.GET)
    public List<OrderQueryModel> getOrders(){
        FindOrdersQuery findOrdersQuery = new FindOrdersQuery();
        List<OrderQueryModel> orders = queryGateway.query(findOrdersQuery, ResponseTypes.multipleInstancesOf(OrderQueryModel.class)).join();
        return orders;
    }

    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public Order getOrder(@RequestParam("orderId") String orderId){
        System.out.println(orderId);
        Order order = ordersService.findByOrderId(orderId);
        System.out.println(order.getName());
        return  order;
    }


    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String addOrder(@RequestBody OrderModel orderModel) {
        System.out.println("Add: "+orderModel.getName());
        CreateOrderCommand command = CreateOrderCommand.builder()
                ._id(UUID.randomUUID().toString())
                .name(orderModel.getName())
                .email(orderModel.getEmail())
                .phone(orderModel.getPhone())
                .time(orderModel.getTime())
                .reserve_date(orderModel.getReserve_date())
                .room(orderModel.getRoom())
                .microphone(orderModel.getMicrophone())
                .foodMenu(orderModel.getFoodMenu())
                .drinkMenu(orderModel.getDrinkMenu())
                .result(orderModel.getResult())
                .status(orderModel.getStatus())
                .build();
        String result;
        try {
            commandGateway.sendAndWait(command);

            Order order = new Order();
            BeanUtils.copyProperties(command, order);
            ordersService.addOrder(order);

            String message = messageService.messageAddOrder(order);
            new SendEmailService(order.getEmail()).sendMail("ร้าน SOP KARAOKE ได้รับคำสั่งซื้อของคุณแล้ว", message);

            result = "AddOrder Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }

    @RequestMapping(value="/updateStatusOrder", method = RequestMethod.POST)
    public String updateOrder(@RequestParam("orderId") String orderId, @RequestParam("status") String status){
        System.out.println("Update: "+orderId);
        System.out.println("Update: "+status);
        Order order = ordersService.findByOrderId(orderId);
        UpdateOrderCommand command = UpdateOrderCommand.builder()
                ._id(orderId)
                .name(order.getName())
                .email(order.getEmail())
                .phone(order.getPhone())
                .time(order.getTime())
                .reserve_date(order.getReserve_date())
                .room(order.getRoom())
                .microphone(order.getMicrophone())
                .foodMenu(order.getFoodMenu())
                .drinkMenu(order.getDrinkMenu())
                .result(order.getResult())
                .status(status)
                .build();
        String result;
        try {
             commandGateway.sendAndWait(command);

             Order orderUpdate = new Order();
             BeanUtils.copyProperties(command, orderUpdate);
             ordersService.updateOrder(orderUpdate);
             result = "UpdateOrder Complete";

             if(orderUpdate.getStatus().equals("payment")){
                 String message = messageService.messagePayment(orderUpdate);
                 new SendEmailService(orderUpdate.getEmail()).sendMail("แจ้งเตือนชำระเงิน SOP KARAOKE", message);
             }

             if(orderUpdate.getStatus().equals("cancel")){
                 String message = messageService.messageCancelOrder(orderUpdate);
                 new SendEmailService(orderUpdate.getEmail()).sendMail("คำสั่งซื้อ SOP KARAOKE ของคุณถูกยกเลิก", message);
             }

        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }


    @RequestMapping(value="/delOrder", method = RequestMethod.DELETE)
    public String deleteOrder(@RequestParam("orderId") String orderId) {
        System.out.println("Del: "+orderId);
        DelOrderCommand command = DelOrderCommand.builder()
                ._id(orderId)
                .build();
        String result;
        try {
            commandGateway.sendAndWait(command);

            ordersService.delOrder(command.get_id());
            result = "DelOrder Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }





    @RabbitListener(queues = "PaymentQueue")
    public void paymentComplete(String orderId){
        System.out.println("RabbitListener_PaymentComplete_OrderId: "+orderId);
        Order order = ordersService.findByOrderId(orderId);
        UpdateOrderCommand command = UpdateOrderCommand.builder()
                ._id(orderId)
                .name(order.getName())
                .email(order.getEmail())
                .phone(order.getPhone())
                .time(order.getTime())
                .reserve_date(order.getReserve_date())
                .room(order.getRoom())
                .microphone(order.getMicrophone())
                .foodMenu(order.getFoodMenu())
                .drinkMenu(order.getDrinkMenu())
                .result(order.getResult())
                .status("complete")
                .build();
        try {
            commandGateway.sendAndWait(command);
            Order orderUpdate = new Order();
            BeanUtils.copyProperties(command, orderUpdate);
            ordersService.updateOrder(orderUpdate);
            System.out.println(command.getStatus());

            if(command.getStatus().equals("complete")){
                System.out.println("rabbitSendFood");
                List<String> foodMenuList = new ArrayList<>();
                if(command.getFoodMenu() != null){
                    foodMenuList.add(command.getFoodMenu().get_id());
                }
                if(command.getDrinkMenu() != null){
                    foodMenuList.add(command.getDrinkMenu().get_id());
                }
                rabbitTemplate.convertAndSend("MyOrderDirectExchange", "reducemenu", foodMenuList);
            }
            System.out.println("UpdateOrder Complete");
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }


}
