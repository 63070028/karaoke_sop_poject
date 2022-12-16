package com.example.roomservice.controller.event;




import com.example.roomservice.pojo.Room;
import com.example.roomservice.service.RoomsService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoomEventsHandler {
    private final RoomsService roomsService;

    public RoomEventsHandler(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @EventHandler
    public void add(CreateRoomEvent event){
        Room accessory = new Room();
        BeanUtils.copyProperties(event, accessory);
        roomsService.addRoom(accessory);
    }

    @EventHandler
    public void update(UpdateRoomEvent event){
        Room accessory = new Room();
        BeanUtils.copyProperties(event, accessory);
        Room accessoryFind = roomsService.findByRoomId(event.get_id());
        if(accessoryFind != null)
            roomsService.updateRoom(accessory);
    }

    @EventHandler
    public void del(DelRoomEvent event){
        roomsService.delRoom(event.get_id());
    }





}
