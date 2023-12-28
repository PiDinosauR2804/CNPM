package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddResidentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String id;
    private int noRoom;
    private String name;
    private String gender;
    private String birthDate;
    private String birthPlace;
    private String job;
    private String phoneNumber;
    private String relationshipWithOwner;

    // Constructor mặc định
    public AddResidentRequest() {
    }

    // Constructor với tham số
    public AddResidentRequest(String id,int noRoom , String name, String gender, String birthDate, String birthPlace, String job,
                    String phoneNumber, String relationshipWithOwner) {
        this.id = id;
        this.noRoom = noRoom;
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoom(int noRoom) {
        this.noRoom = noRoom;
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
    
    // Getter and Setter methods for 'noRoom'
    public int getNoRoom() {
        return noRoom;
    }

    // Getter and Setter methods for 'relationshipWithOwner'
    public String getRelationshipWithOwner() {
        return relationshipWithOwner;
    }

    public void setRelationshipWithOwner(String relationshipWithOwner) {
        this.relationshipWithOwner = relationshipWithOwner;
    }
}
