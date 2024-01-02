package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String idRes;
    private String name;
    private String gender;
    private String birthDate;
    private String birthPlace;
    private String job;
    private String phoneNumber;
    private String relationshipWithOwner;

    @ManyToOne
    @JoinColumn(name = "idRoom", referencedColumnName = "id")
    private Room room;


    // Constructor mặc định
    public Resident() {
    }

    // Constructor với tham số
    public Resident(String idRes, String name, String gender, String birthDate, String birthPlace, String job,
                    String phoneNumber, String relationshipWithOwner) {
        this.idRes = idRes;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.relationshipWithOwner = relationshipWithOwner;
    }

    public int getNo() {
        return no;
    }

    // Getter and Setter methods for 'id'
    public String getIdRes() {
        return idRes;
    }

    public void setIdRes(String idRes) {
        this.idRes = idRes;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getkey() {
        return room.getKey();
    }

    public String getidOwner() {
        return room.getIdOwner();
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

    // Getter and Setter methods for 'birthDate'
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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
    // public String getKeyRoom() {
    //     return keyRoom;
    // }

    // public void setKeyRoom(String keyRoom) {
    //     this.keyRoom = keyRoom;
    // }
    
    // Getter and Setter methods for 'noRoom'
    public int getNoRoom() {
        return room.getNoRoom();
    }

    // Getter and Setter methods for 'relationshipWithOwner'
    public String getRelationshipWithOwner() {
        return relationshipWithOwner;
    }

    public void setRelationshipWithOwner(String relationshipWithOwner) {
        this.relationshipWithOwner = relationshipWithOwner;
    }
}
