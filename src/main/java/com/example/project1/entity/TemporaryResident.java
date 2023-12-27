package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class TemporaryResident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String name;
    private String id;
    private String noRoom;
    private String keyRoom;
    private String dayIn;
    private String dayOut;
    
    public TemporaryResident() {}

    public TemporaryResident(String string, String string2, String string3, String string4, int i) {
    }

    public TemporaryResident(String name, String id, String noRoom, String keyRoom, String dayIn, String dayOut) {
        this.name = name;
        this.id = id;
        this.noRoom = noRoom;
        this.keyRoom = keyRoom;
        this.dayIn = dayIn;
        this.dayOut = dayOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(String noRoom) {
        this.noRoom = noRoom;
    }

    public String getKeyRoom() {
        return keyRoom;
    }

    public void setKeyRoom(String keyRoom) {
        this.keyRoom = keyRoom;
    }

    public String getDayIn() {
        return dayIn;
    }

    public void setDayIn(String dayIn) {
        this.dayIn = dayIn;
    }

    public String getDayOut() {
        return dayOut;
    }

    public void setDayOut(String dayOut) {
        this.dayOut = dayOut;
    }
}
