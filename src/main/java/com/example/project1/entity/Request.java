package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private int noRoom;
    private int id_request;
    private String object_id;
    private String content_changed;
    private int approved = 1;

    public Request() {
    }

    public Request(int noRoom, int id_request, String content_changed) {
        this.noRoom = noRoom;
        this.id_request = id_request;
        this.content_changed = content_changed;
    } 

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getNo() {
        return no;
    }

    public int getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(int noRoom) {
        this.noRoom = noRoom;
    }

    // Getter và setter cho id_request
    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    // Getter và setter cho object_id
    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    // Getter và setter cho content_changed
    public String getContent_changed() {
        return content_changed;
    }

    public void setContent_changed(String content_changed) {
        this.content_changed = content_changed;
    }
}
