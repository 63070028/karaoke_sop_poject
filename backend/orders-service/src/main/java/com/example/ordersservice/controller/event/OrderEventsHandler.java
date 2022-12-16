package com.example.ordersservice.controller.event;


import com.example.ordersservice.pojo.Order;
import com.example.ordersservice.service.OrdersService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {
    private final OrdersService ordersService;

    public OrderEventsHandler(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @EventHandler
    public void add(CreateOrderEvent event){
        Order order = new Order();
        BeanUtils.copyProperties(event, order);
        ordersService.addOrder(order);


    }

    @EventHandler
    public void update(UpdateOrderEvent event){
        Order order = new Order();
        BeanUtils.copyProperties(event, order);
        ordersService.updateOrder(order);
    }

    @EventHandler
    public void del(DelOrderEvent event){
        ordersService.delOrder(event.get_id());
    }

}
