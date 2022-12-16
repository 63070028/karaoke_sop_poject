package com.example.foodmenuservice.service;


import com.example.foodmenuservice.pojo.FoodMenu;
import com.example.foodmenuservice.repository.FoodMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodMenuService {

    @Autowired
    private FoodMenuRepository foodMenuRepository;

    public List<FoodMenu> showFoodMenu() {return foodMenuRepository.findAll();}

    public boolean addFoodMenu(FoodMenu foodMenu){
        try {
            foodMenuRepository.save(foodMenu);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateFoodMenu(FoodMenu foodMenu){
        try {
            foodMenuRepository.save(foodMenu);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delFoodMenu(String id) {
        try {
            foodMenuRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public FoodMenu findByFoodMenuId(String id){
        try {
            FoodMenu foodMenu = foodMenuRepository.findFoodMenuByFoodMenuId(id);
            return foodMenu;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
