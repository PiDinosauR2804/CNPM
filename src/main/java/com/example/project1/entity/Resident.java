package com.example.project1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
public class Resident {
    @ManyToOne
    @JoinColumn(name = "key")
    private Room room;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String id;
    private String name;
    private String gender;
    private String birthPlace;
    private String job;
    private String phoneNumber;
    private String keyRoom;
    private int noRoom;
    private String relationshipWithOwner;
    private String idOwner;

    // Constructor mặc định
    public Resident() {
    }

    // Constructor với tham số
    public Resident(String id, String name, String gender, String birthPlace, String job,
                    String phoneNumber, String keyRoom, int noRoom, String relationshipWithOwner,
                    String idOwner) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthPlace = birthPlace;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.keyRoom = keyRoom;
        this.noRoom = noRoom;
        this.relationshipWithOwner = relationshipWithOwner;
        this.idOwner = idOwner;
    }

    // Getter and Setter methods for 'id'
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter methods for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for 'gender'
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter methods for 'birthPlace'
    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    // Getter and Setter methods for 'job'
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    // Getter and Setter methods for 'phoneNumber'
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter methods for 'keyRoom'
    public String getKeyRoom() {
        return keyRoom;
    }

    public void setKeyRoom(String keyRoom) {
        this.keyRoom = keyRoom;
    }
    
    // Getter and Setter methods for 'noRoom'
    public int getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(int noRoom) {
        this.noRoom = noRoom;
    }

    // Getter and Setter methods for 'relationshipWithOwner'
    public String getRelationshipWithOwner() {
        return relationshipWithOwner;
    }

    public void setRelationshipWithOwner(String relationshipWithOwner) {
        this.relationshipWithOwner = relationshipWithOwner;
    }

    // Getter and Setter methods for 'idOwner'
    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }
    



}
