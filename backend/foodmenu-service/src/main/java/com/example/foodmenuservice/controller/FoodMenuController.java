package com.example.foodmenuservice.controller;


import com.example.foodmenuservice.controller.command.CreateFoodMenuCommand;
import com.example.foodmenuservice.controller.command.DelFoodMenuCommand;
import com.example.foodmenuservice.controller.command.UpdateFoodMenuCommand;
import com.example.foodmenuservice.controller.command.model.FoodMenuModel;
import com.example.foodmenuservice.controller.command.model.FoodMenuUpdateModel;
import com.example.foodmenuservice.controller.query.FindFoodMenuQuery;
import com.example.foodmenuservice.controller.query.FoodMenuQueryModel;
import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.foodmenuservice.service.FoodMenuService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
public class FoodMenuController {


    private final CommandGateway commandGateway;
    private  final QueryGateway queryGateway;

    private FoodMenuService foodMenuService;


    @Autowired
    public FoodMenuController(CommandGateway commandGateway, QueryGateway queryGateway, FoodMenuService foodMenuService) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.foodMenuService = foodMenuService;
    }

    @RequestMapping(value = "/getAllFoodMenu", method = RequestMethod.GET)
    public List<FoodMenuQueryModel> getFoodMenu(){
        FindFoodMenuQuery findFoodMenuQuery = new FindFoodMenuQuery();
        List<FoodMenuQueryModel> foodMenu = queryGateway.query(findFoodMenuQuery, ResponseTypes.multipleInstancesOf(FoodMenuQueryModel.class)).join();
        return foodMenu;
    }

//    @RequestMapping(value = "/getFoodMenu", method = RequestMethod.GET)
//    public FoodMenu getFoodMenu(@RequestParam("foodMenuId") String foodMenuId){
//        System.out.println(foodMenuId);
//        FoodMenu foodMenu = foodMenuService.findByFoodMenuId(foodMenuId);
//        System.out.println(foodMenu.getName());
//        return  foodMenu;
//    }


    @RequestMapping(value = "/addFoodMenu", method = RequestMethod.POST)
    public String addFoodMenu(@RequestBody FoodMenuModel foodMenu) {
        System.out.println("Add: "+foodMenu.getName());
        CreateFoodMenuCommand command = CreateFoodMenuCommand.builder()
                ._id(UUID.randomUUID().toString())
                .name(foodMenu.getName())
                .price(foodMenu.getPrice())
                .foods(foodMenu.getFoods())
                .quantity(foodMenu.getQuantity())
                .build();
        String result;
        try {
            commandGateway.sendAndWait(command);
            result = "AddFoodMenu Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }
//
    @RequestMapping(value="/updatFoodMenu", method = RequestMethod.POST)
    public String updateFoodMenu(@RequestBody FoodMenuUpdateModel foodMenu){
        System.out.println("Update: "+foodMenu.get_id());
        UpdateFoodMenuCommand command = UpdateFoodMenuCommand.builder()
                ._id(foodMenu.get_id())
                .name(foodMenu.getName())
                .price(foodMenu.getPrice())
                .foods(foodMenu.getFoods())
                .quantity(foodMenu.getQuantity())
                .build();
        String result;
        try {
             commandGateway.sendAndWait(command);
             result = "UpdateFoodMenu Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }

    @RequestMapping(value="/delFoodMenu", method = RequestMethod.DELETE)
    public String deleteFoodMenu(@RequestParam("foodMenuId") String foodMenuId) {
        System.out.println("Del: "+foodMenuId);
        DelFoodMenuCommand command = DelFoodMenuCommand.builder()
                ._id(foodMenuId)
                .build();
        String result;
        try {
            commandGateway.sendAndWait(command);
            result = "DelFoodMenu Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }

    @RabbitListener(queues = "reduceFoodMenuQueue")
    public void reduceFoodMenu(List<FoodMenu> foodMenuList){
        for (FoodMenu foodMenu: foodMenuList) {
            if(foodMenu != null){
                System.out.println("Update: "+foodMenu.get_id());
                UpdateFoodMenuCommand command = UpdateFoodMenuCommand.builder()
                        ._id(foodMenu.get_id())
                        .name(foodMenu.getName())
                        .price(foodMenu.getPrice())
                        .foods(foodMenu.getFoods())
                        .quantity(foodMenu.getQuantity()-1)
                        .build();
                try {
                    commandGateway.sendAndWait(command);
                    System.out.println("UpdateFoodMenu Complete");
                }
                catch (Exception e){
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }



}
