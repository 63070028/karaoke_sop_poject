package com.example.roomservice.repository;


import com.example.roomservice.pojo.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query(value = "SELECT * FROM ROOMS WHERE _ID = ?1", nativeQuery = true)
    Room findByRoomId(String _id);
}
