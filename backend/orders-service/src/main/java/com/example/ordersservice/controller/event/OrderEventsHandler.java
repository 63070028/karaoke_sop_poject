//package com.example.ordersservice.controller.event;
//
//
//import com.example.foodmenuservice.pojo.FoodMenu;
//import com.example.ordersservice.pojo.Order;
//import com.example.ordersservice.service.OrdersService;
//import org.axonframework.eventhandling.EventHandler;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class OrderEventsHandler {
//    private final OrdersService ordersService;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public OrderEventsHandler(OrdersService ordersService) {
//        this.ordersService = ordersService;
//    }
//
//
//    @EventHandler
//    public void add(CreateOrderEvent event){
//        Order order = new Order();
//        BeanUtils.copyProperties(event, order);
//        ordersService.addOrder(order);
//    }
//
//    @EventHandler
//    public void update(UpdateOrderEvent event){
//        Order order = new Order();
//        BeanUtils.copyProperties(event, order);
//        ordersService.updateOrder(order);
//        System.out.println(event.getStatus());
//
//        if(event.getStatus().equals("complete")){
//            System.out.println("rabbitSendFood");
//            List<String> foodMenuList = new ArrayList<>();
//            if(event.getFoodMenu() != null){
//                foodMenuList.add(event.getFoodMenu().get_id());
//            }
//            if(event.getDrinkMenu() != null){
//                foodMenuList.add(event.getDrinkMenu().get_id());
//            }
//            rabbitTemplate.convertAndSend("MyOrderDirectExchange", "reducemenu", foodMenuList);
//        }
//
//    }
//
//    @EventHandler
//    public void del(DelOrderEvent event){
//        System.out.println("rabbitSendFood");
//        ordersService.delOrder(event.get_id());
//    }
//
//}
