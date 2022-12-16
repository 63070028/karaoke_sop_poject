package com.example.ordersservice.service;

import com.example.ordersservice.pojo.Order;
import com.example.ordersservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> showOrders() {return orderRepository.findAll();}

    public boolean addOrder(Order order){
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateOrder(Order order){
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delOrder(String id) {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Order findByOrderId(String id){
        try {
            Order order = orderRepository.findOrderByOrderId(id);
            return order;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
