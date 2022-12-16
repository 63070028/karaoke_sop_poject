package com.example.ordersservice.controller;


import com.example.ordersservice.controller.command.CreateOrderCommand;
import com.example.ordersservice.controller.command.DelOrderCommand;
import com.example.ordersservice.controller.command.OrdersModel;
import com.example.ordersservice.controller.command.UpdateOrderCommand;
import com.example.ordersservice.controller.query.FindOrdersQuery;
import com.example.ordersservice.controller.query.OrderQueryModel;
import com.example.ordersservice.pojo.Order;
import com.example.ordersservice.service.OrdersService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {


    private final CommandGateway commandGateway;
    private  final QueryGateway queryGateway;

    private OrdersService ordersService;


    @Autowired
    public OrderController(CommandGateway commandGateway, QueryGateway queryGateway, OrdersService ordersService) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.ordersService = ordersService;
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
    public String addOrder(@RequestBody OrdersModel order) {
        System.out.println("Add: "+order.getName());
        CreateOrderCommand command = CreateOrderCommand.builder()
                ._id(UUID.randomUUID().toString())
                .name(order.getName())
                .email(order.getEmail())
                .reserve_date(order.getReserve_date())
                .status(order.getStatus())
                .time(order.getTime())

                .roomId(order.getRoomId())
                .roomName(order.getRoomName())
                .accessoryName(order.getAccessoryName())
                .microphone(order.getMicrophone())

                .foodMenuName(order.getFoodMenuName())
                .foodMenuSetId(order.getFoodMenuSetId())
                .drinkMenuSetId(order.getDrinkMenuSetId())
                .drinkMenuName(order.getDrinkMenuName())

                .result(order.getResult())
                .build();
        String result;
        try {
            commandGateway.sendAndWait(command);
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
                .reserve_date(order.getReserve_date())
                .status(status)
                .time(order.getTime())

                .roomId(order.getRoomId())
                .roomName(order.getRoomName())
                .accessoryName(order.getAccessoryName())
                .microphone(order.getMicrophone())

                .foodMenuName(order.getFoodMenuName())
                .foodMenuSetId(order.getFoodMenuSetId())
                .drinkMenuSetId(order.getDrinkMenuSetId())
                .drinkMenuName(order.getDrinkMenuName())

                .result(order.getResult())
                .build();
        String result;
        try {
             commandGateway.sendAndWait(command);
             result = "UpdateOrder Complete";
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
            result = "DelOrder Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }



}
