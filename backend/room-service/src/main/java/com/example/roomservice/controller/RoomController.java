package com.example.roomservice.controller;


import com.example.roomservice.controller.command.CreateRoomCommand;
import com.example.roomservice.controller.command.DelRoomCommand;
import com.example.roomservice.controller.command.Model.RoomModel;
import com.example.roomservice.controller.command.Model.RoomUpdateModel;
import com.example.roomservice.controller.command.Model.Rooms;
import com.example.roomservice.controller.command.UpdateRoomCommand;
import com.example.roomservice.controller.query.FindRoomsQuery;
import com.example.roomservice.controller.query.RoomQueryModel;
import com.example.roomservice.service.RoomsService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RoomController {


    private final CommandGateway commandGateway;
    private  final QueryGateway queryGateway;

    private RoomsService roomsService;

    @Autowired
    public RoomController(CommandGateway commandGateway, QueryGateway queryGateway, RoomsService roomsService) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.roomsService = roomsService;
    }




    @RequestMapping(value = "/getRooms", method = RequestMethod.GET)
    public List<RoomQueryModel> getRooms(){
        FindRoomsQuery findRoomsQuery = new FindRoomsQuery();
        List<RoomQueryModel> rooms = queryGateway.query(findRoomsQuery, ResponseTypes.multipleInstancesOf(RoomQueryModel.class)).join();
        return rooms;
    }



    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public String addRoom(@RequestBody RoomModel room) {
        System.out.println("Add: "+room.getName());
        CreateRoomCommand command = CreateRoomCommand.builder()
                ._id(UUID.randomUUID().toString())
                .accessory(room.getAccessory())
                .detail(room.getDetail())
                .name(room.getName())
                .price(room.getPrice())
                .build();

        String result;
        try {
            commandGateway.sendAndWait(command);
            result = "addRoom Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }

    @RequestMapping(value = "/addRooms", method = RequestMethod.POST)
    public String addRoom(@RequestBody Rooms rooms) {
        String result;
        for (RoomModel room: rooms.getRooms()){
            System.out.println("Add: "+room.getName());
            CreateRoomCommand command = CreateRoomCommand.builder()
                    ._id(UUID.randomUUID().toString())
                    .accessory(room.getAccessory())
                    .detail(room.getDetail())
                    .name(room.getName())
                    .price(room.getPrice())
                    .build();
            try {
                commandGateway.sendAndWait(command);
            }
            catch (Exception e){
                result = e.getLocalizedMessage();
            }

        }

        result = "addRooms Complete";
        return  result;
    }

    @RequestMapping(value="/updateRoom", method = RequestMethod.POST)
    public String updateRoom(@RequestBody RoomUpdateModel room){
        System.out.println("Update: "+room.getName());
        UpdateRoomCommand command = UpdateRoomCommand.builder()
                ._id(room.get_id())
                .accessory(room.getAccessory())
                .detail(room.getDetail())
                .name(room.getName())
                .price(room.getPrice())
                .build();
        String result;
        try {
             commandGateway.sendAndWait(command);
             result = "UpdateRoom Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }

    @RequestMapping(value="/delRoom", method = RequestMethod.DELETE)
    public String deleteRoom(@RequestParam("_id") String _id) {
        System.out.println("Del: "+_id);
        DelRoomCommand command = DelRoomCommand.builder()
                ._id(_id)
                .build();
        String result;
        try {
            commandGateway.sendAndWait(command);
            result = "DelRoom Complete";
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return  result;
    }






}
