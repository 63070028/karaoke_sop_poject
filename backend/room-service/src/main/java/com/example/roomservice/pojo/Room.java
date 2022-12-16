package com.example.roomservice.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rooms")
public class Room implements Serializable {


    @Id
    @Column(unique = true)
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
