package com.example.roomservice.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document("Rooms")
public class Room implements Serializable {


    @Id
    private String _id;
    private String name;
    private String accessory;
    private int price;
    private String detail;



    public Room() {
    }

    public Room(String _id, String name, String accessory, int price, String detail) {
        this._id = _id;
        this.name = name;
        this.accessory = accessory;
        this.price = price;
        this.detail = detail;
    }
}
