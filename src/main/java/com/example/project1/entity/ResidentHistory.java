package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;


@Entity
public class ResidentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String id;
    private String name;
    private String gender;
    private String birthDate;
    private String birthPlace;
    private String job;
    private String phoneNumber;
    private String relationshipWithOwner;
    private String dayIn;
    private String dayOut;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "noRoom", referencedColumnName = "noRoom"),
        @JoinColumn(name = "`key`", referencedColumnName = "`key`"),
        @JoinColumn(name = "idOwner", referencedColumnName = "idOwner")
    })
    private RoomHistory roomHistory;


    // Constructor mặc định
    public ResidentHistory() {
    }

    // Constructor với tham số
    public ResidentHistory(String id, String name, String gender, String birthDate, String birthPlace, String job,
                    String phoneNumber, String relationshipWithOwner, String dayIn) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.relationshipWithOwner = relationshipWithOwner;
        this.dayIn = dayIn;
    }

    // Getter and Setter methods for 'id'
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoom(RoomHistory roomHistory) {
        this.roomHistory = roomHistory;
    }

    public String getkey() {
        return roomHistory.getKey();
    }

    public String getidOwner() {
        return roomHistory.getIdOwner();
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
        return roomHistory.getNoRoom();
    }

    // Getter and Setter methods for 'relationshipWithOwner'
    public String getRelationshipWithOwner() {
        return relationshipWithOwner;
    }

    public void setRelationshipWithOwner(String relationshipWithOwner) {
        this.relationshipWithOwner = relationshipWithOwner;
    }

    public String getDayOut() {
        return dayOut;
    }

    public String getDayIn() {
        return dayIn;
    }

    public void setDayOut(String dayOut) {
        this.dayOut = dayOut;
    }

    public void setDayIn(String dayIn) {
        this.dayIn = dayIn;
    }
}
