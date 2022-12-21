package com.example.roomservice.controller.command;



import com.example.roomservice.controller.event.CreateRoomEvent;
import com.example.roomservice.controller.event.DelRoomEvent;
import com.example.roomservice.controller.event.UpdateRoomEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class RoomAggregate {

    @AggregateIdentifier
    private String _id;
    private String name;
    private String accessory;
    private int price;
    private String detail;

    public RoomAggregate() {
    }

    @CommandHandler
    public RoomAggregate(CreateRoomCommand command){
        System.out.println("CreateRoomCommand");
        CreateRoomEvent event = new CreateRoomEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(CreateRoomEvent event){
        this._id = event.get_id();
        this.price = event.getPrice();
        this.detail = event.getDetail();
        this.accessory = event.getAccessory();
        this.name = event.getName();
    }

    @CommandHandler
    public void handle(UpdateRoomCommand command){
        System.out.println("UpdateRoomCommand");
        UpdateRoomEvent event = new UpdateRoomEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(UpdateRoomEvent event){
        this._id = event.get_id();
        this.price = event.getPrice();
        this.detail = event.getDetail();
        this.accessory = event.getAccessory();
        this.name = event.getName();
    }


    @CommandHandler
    public void delete(DelRoomCommand command){
        System.out.println("DelRoomCommand");
        DelRoomEvent event = new DelRoomEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
        AggregateLifecycle.markDeleted();
    }




}
