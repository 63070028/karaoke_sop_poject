package com.example.ordersservice.controller.command;


import com.example.ordersservice.controller.event.CreateOrderEvent;
import com.example.ordersservice.controller.event.DelOrderEvent;
import com.example.ordersservice.controller.event.UpdateOrderEvent;
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
    private String reserve_date;
    private String time;
    private int result;
    private String status;

    private String roomId;
    private String roomName;
    private String accessoryName;
    private int microphone;

    private String foodMenuSetId;
    private String foodMenuName;

    private String drinkMenuSetId;
    private  String drinkMenuName;


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
        this.reserve_date = event.getReserve_date();
        this.status = event.getStatus();
        this.time = event.getTime();
        this.roomId = event.getRoomId();
        this.roomName = event.getRoomName();
        this.accessoryName = event.getAccessoryName();
        this.microphone = event.getMicrophone();
        this.foodMenuName = event.getFoodMenuName();
        this.foodMenuSetId = event.getFoodMenuSetId();
        this.drinkMenuSetId = event.getDrinkMenuSetId();
        this.drinkMenuName = event.getDrinkMenuName();
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
        this.reserve_date = event.getReserve_date();
        this.status = event.getStatus();
        this.time = event.getTime();
        this.roomId = event.getRoomId();
        this.roomName = event.getRoomName();
        this.accessoryName = event.getAccessoryName();
        this.microphone = event.getMicrophone();
        this.foodMenuName = event.getFoodMenuName();
        this.foodMenuSetId = event.getFoodMenuSetId();
        this.drinkMenuSetId = event.getDrinkMenuSetId();
        this.drinkMenuName = event.getDrinkMenuName();
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
