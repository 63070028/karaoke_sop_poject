package com.example.ordersservice.controller.command;


import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.ordersservice.controller.event.CreateOrderEvent;
import com.example.ordersservice.controller.event.DelOrderEvent;
import com.example.ordersservice.controller.event.UpdateOrderEvent;
import com.example.roomservice.pojo.Room;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;


@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String _id;
    private String name;
    private String email;
    private String phone;

    private String reserve_date;
    private String time;

    private Room room;
    private int microphone;
    private FoodMenu foodMenu;
    private FoodMenu drinkMenu;

    private int result;
    private String status;


    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand){
        System.out.println("CreateOrderCommand");
        CreateOrderEvent createOrderEvent = new CreateOrderEvent();
        BeanUtils.copyProperties(createOrderCommand, createOrderEvent);
        AggregateLifecycle.apply(createOrderEvent);
    }
    @EventSourcingHandler
    public void on(CreateOrderEvent event){
        this._id = event.get_id();
        this.name = event.getName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.reserve_date = event.getReserve_date();
        this.time = event.getTime();
        this.room = event.getRoom();
        this.microphone = event.getMicrophone();
        this.foodMenu = event.getFoodMenu();
        this.drinkMenu = event.getDrinkMenu();
        this.status = event.getStatus();
        this.result = event.getResult();
    }

    @CommandHandler
    public void handle(UpdateOrderCommand command){
        System.out.println("UpdateOrderCommand");
        UpdateOrderEvent event = new UpdateOrderEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(UpdateOrderEvent event){
        this._id = event.get_id();
        this.name = event.getName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.reserve_date = event.getReserve_date();
        this.time = event.getTime();
        this.room = event.getRoom();
        this.microphone = event.getMicrophone();
        this.foodMenu = event.getFoodMenu();
        this.drinkMenu = event.getDrinkMenu();
        this.status = event.getStatus();
        this.result = event.getResult();
    }


    @CommandHandler
    public void delete(DelOrderCommand command){
        System.out.println("DelOrderCommand");
        DelOrderEvent event = new DelOrderEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
        AggregateLifecycle.markDeleted();
    }


}
