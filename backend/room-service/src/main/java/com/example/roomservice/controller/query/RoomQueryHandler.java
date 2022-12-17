package com.example.roomservice.controller.query;


import com.example.roomservice.pojo.Room;
import com.example.roomservice.service.RoomsService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomQueryHandler {
    private RoomsService roomsService;

    public RoomQueryHandler(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @QueryHandler
    List<RoomQueryModel> findRooms(FindRoomsQuery query){
        List<RoomQueryModel> rooms = new ArrayList<>();
        List<Room> storedRooms = roomsService.showRooms();
        for (Room room: storedRooms) {
            RoomQueryModel roomQueryModel = new RoomQueryModel();
            BeanUtils.copyProperties(room, roomQueryModel);
            rooms.add(roomQueryModel);
        }
        return  rooms;
    }
}
