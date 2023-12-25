package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class MandatoryFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private int month;
    private int year;

    private int roomFee;
    private int roomFeePaid;
    private int waterFee;
    private int waterFeePaid;
    private int electricFee;
    private int electricFeePaid;
    private int parkingFee;
    private int parkingFeePaid;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "`key`", referencedColumnName = "`key`"),
        @JoinColumn(name = "noRoom", referencedColumnName = "noRoom")
    })
    private Room room;
    
    public MandatoryFee() {};
    public MandatoryFee(int month, int year, int roomFee, int waterFee, int electricFee, int parkingFee) {
        this.month = month;
        this.year = year;
        this.roomFee = roomFee;
        this.waterFee = waterFee;
        this.electricFee = electricFee;
        this.parkingFee = parkingFee;
    }

    public int getNoRoom() {
        return room.getNoRoom();
    }

    public String getKey() {
        return room.getKey();
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getRoomFee() {
        return roomFee;
    }

    public int getRoomFeePaid() {
        return roomFeePaid;
    }

    public int getWaterFee() {
        return waterFee;
    }

    public int getWaterFeePaid() {
        return waterFeePaid;
    }

    public int getElectricFee() {
        return electricFee;
    }

    public int getElectricFeePaid() {
        return electricFeePaid;
    }

    public int getParkingFee() {
        return parkingFee;
    }

    public int getParkingFeePaid() {
        return parkingFeePaid;
    }

    public Room getRoom() {
        return room;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRoomFee(int roomFee) {
        this.roomFee = roomFee;
    }

    public void setRoomFeePaid(int roomFeePaid) {
        this.roomFeePaid = roomFeePaid;
    }

    public void setWaterFee(int waterFee) {
        this.waterFee = waterFee;
    }

    public void setWaterFeePaid(int waterFeePaid) {
        this.waterFeePaid = waterFeePaid;
    }

    public void setElectricFee(int electricFee) {
        this.electricFee = electricFee;
    }

    public void setElectricFeePaid(int electricFeePaid) {
        this.electricFeePaid = electricFeePaid;
    }

    public void setParkingFee(int parkingFee) {
        this.parkingFee = parkingFee;
    }

    public void setParkingFeePaid(int parkingFeePaid) {
        this.parkingFeePaid = parkingFeePaid;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
