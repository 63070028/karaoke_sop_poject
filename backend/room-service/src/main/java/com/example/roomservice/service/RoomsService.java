package com.example.roomservice.service;


import com.example.roomservice.pojo.Room;
import com.example.roomservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> showRooms() {return roomRepository.findAll();}

    public boolean addRoom(Room room){
        try {
            roomRepository.save(room);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateRoom(Room room){
        try {
            roomRepository.save(room);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delRoom(String id) {
        try {
            roomRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Room findByRoomId(String id){
        try {
            Room room = roomRepository.findByRoomId(id);
            return room;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
