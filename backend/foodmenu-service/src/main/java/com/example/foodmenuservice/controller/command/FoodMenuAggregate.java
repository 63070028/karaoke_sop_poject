package com.example.foodmenuservice.controller.command;



import com.example.foodmenuservice.controller.event.CreateFoodMenuEvent;
import com.example.foodmenuservice.controller.event.DelFoodMenuEvent;
import com.example.foodmenuservice.controller.event.UpdateFoodMenuEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


@Aggregate
public class FoodMenuAggregate {

    @AggregateIdentifier
    private String _id;
    private String name;
    private String type;

    private List<String> foods;
    private int price;
    private int quantity;



    public FoodMenuAggregate() {
    }

    @CommandHandler
    public FoodMenuAggregate(CreateFoodMenuCommand command){
        System.out.println("CreateFoodMenuCommand");
        System.out.println(command.getFoods());
        CreateFoodMenuEvent event = new CreateFoodMenuEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(CreateFoodMenuEvent event){
        this._id = event.get_id();
        this.name = event.getName();
        this.price = event.getPrice();
        this.foods = event.getFoods();
        this.quantity = event.getQuantity();
    }

    @CommandHandler
    public void handle(UpdateFoodMenuCommand command){
        System.out.println("UpdateFoodMenuCommand");
        UpdateFoodMenuEvent event = new UpdateFoodMenuEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(UpdateFoodMenuEvent event){
        this._id = event.get_id();
        this.name = event.getName();
        this.price = event.getPrice();
        this.foods = event.getFoods();
        this.quantity = event.getQuantity();
    }


    @CommandHandler
    public void delete(DelFoodMenuCommand command){
        System.out.println("DelFoodMenuCommand");
        DelFoodMenuEvent event = new DelFoodMenuEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
        AggregateLifecycle.markDeleted();
    }


}
