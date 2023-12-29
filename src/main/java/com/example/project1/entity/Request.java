package com.example.project1.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.project1.Repository.TypeDonationRepository;

import aj.org.objectweb.asm.Type;
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
    private int idRequest;
    private String objectId;
    private String contentChanged;
    private String objectName;
    private String donationName;
    private int approved = 1;

    public Request() {
    }

    public Request(int noRoom, int idRequest, String contentChanged) {
        this.noRoom = noRoom;
        this.idRequest = idRequest;
        this.contentChanged = contentChanged;
    } 

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }    

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
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

    // Getter và setter cho idRequest
    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    // Getter và setter cho objectId
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    // Getter và setter cho contentChanged
    public String getContentChanged() {
        return contentChanged;
    }

    public void setContentChanged(String contentChanged) {
        this.contentChanged = contentChanged;
    }
}
