package com.example.foodmenuservice.controller.event;


import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.foodmenuservice.service.FoodMenuService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FoodMenuEventsHandler {
    private final FoodMenuService foodMenuService;

    public FoodMenuEventsHandler(FoodMenuService foodMenuService) {
        this.foodMenuService = foodMenuService;
    }

    @EventHandler
    public void add(CreateFoodMenuEvent event){
        FoodMenu order = new FoodMenu();
        BeanUtils.copyProperties(event, order);
        foodMenuService.addFoodMenu(order);
    }

    @EventHandler
    public void update(UpdateFoodMenuEvent event){
        FoodMenu order = new FoodMenu();
        BeanUtils.copyProperties(event, order);
        foodMenuService.updateFoodMenu(order);
    }

    @EventHandler
    public void del(DelFoodMenuEvent event){
        foodMenuService.delFoodMenu(event.get_id());
    }

}
