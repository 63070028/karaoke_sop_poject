package com.example.roomservice.repository;


import com.example.roomservice.pojo.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoomRepository extends MongoRepository<Room, String> {

    @Query(value="{ '_id' : ?0 }")
    Room findByRoomId(String _id);
}
