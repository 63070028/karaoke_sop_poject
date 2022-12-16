package com.example.foodmenuservice.repository;


import com.example.foodmenuservice.pojo.FoodMenu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodMenuRepository extends MongoRepository<FoodMenu, String> {

    @Query(value="{ '_id' : ?0 }")
    public FoodMenu findFoodMenuByFoodMenuId(String orderId);
}
