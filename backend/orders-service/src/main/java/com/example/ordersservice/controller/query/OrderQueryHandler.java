package com.example.ordersservice.controller.query;

import com.example.ordersservice.pojo.Order;
import com.example.ordersservice.service.OrdersService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderQueryHandler {
    private OrdersService ordersService;

    public OrderQueryHandler(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @QueryHandler
    List<OrderQueryModel> findOrders(FindOrdersQuery query){
        List<OrderQueryModel> orders = new ArrayList<>();
        List<Order> storedOrders = ordersService.showOrders();
        for (Order order: storedOrders) {
            OrderQueryModel orderQueryModel = new OrderQueryModel();
            BeanUtils.copyProperties(order, orderQueryModel);
            orders.add(orderQueryModel);
        }
        return  orders;
    }
}
