package com.example.foodmenuservice.controller.query;

import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.foodmenuservice.service.FoodMenuService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FoodMenuQueryHandler {
    private FoodMenuService foodMenuService;

    public FoodMenuQueryHandler(FoodMenuService foodMenuService) {
        this.foodMenuService = foodMenuService;
    }

    @QueryHandler
    List<FoodMenuQueryModel> findFoodMenu(FindFoodMenuQuery query){
        List<FoodMenuQueryModel> orders = new ArrayList<>();
        List<FoodMenu> storedFoodMenu = foodMenuService.showFoodMenu();
        for (FoodMenu order: storedFoodMenu) {
            FoodMenuQueryModel foodMenuQueryModel = new FoodMenuQueryModel();
            BeanUtils.copyProperties(order, foodMenuQueryModel);
            orders.add(foodMenuQueryModel);
        }
        return  orders;
    }
}
