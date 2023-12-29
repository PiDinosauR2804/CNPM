package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    private int roomFeePaid;
    private int waterFee;
    private int waterFeePaid;
    private int electricFee;
    private int electricFeePaid;
    private int parkingFeePaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`key`", referencedColumnName = "`key`", updatable = true)
    // @JoinColumns({
    //     @JoinColumn(name = "defaultParkingFee", referencedColumnName = "defaultParkingFee", updatable = true),
    //     @JoinColumn(name = "defaultFeeRoom", referencedColumnName = "defaultFeeRoom", updatable = true),
    //     @JoinColumn(name = "`key`", referencedColumnName = "`key`", updatable = true),
    //     @JoinColumn(name = "noRoom", referencedColumnName = "noRoom", updatable = true),
    // })
    private Room room;
    
    public MandatoryFee() {};
    public MandatoryFee(int month, int year, int waterFee, int electricFee) {
        this.month = month;
        this.year = year;
        this.waterFee = waterFee;
        this.electricFee = electricFee;
    }

    public int getNo() {
        return no;
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

    public int getDefaultFeeRoom() {
        return room.getDefaultFeeRoom();
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

    public int getDefaultParkingFee() {
        return room.getDefaultParkingFee();
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
        room.setDefaultFeeRoom(roomFee);
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
        room.setDefaultParkingFee(parkingFee);
    }

    public void setParkingFeePaid(int parkingFeePaid) {
        this.parkingFeePaid = parkingFeePaid;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
