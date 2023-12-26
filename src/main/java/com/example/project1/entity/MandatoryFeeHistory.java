package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class MandatoryFeeHistory {
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

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "defaultParkingFee", referencedColumnName = "defaultParkingFee"),
        @JoinColumn(name = "defaultFeeRoom", referencedColumnName = "defaultFeeRoom"),
        @JoinColumn(name = "`key`", referencedColumnName = "`key`"),
        @JoinColumn(name = "noRoom", referencedColumnName = "noRoom"),
    })
    private RoomHistory roomHisotry;
    
    public MandatoryFeeHistory() {};
    public MandatoryFeeHistory(int month, int year, int waterFee, int electricFee) {
        this.month = month;
        this.year = year;
        this.waterFee = waterFee;
        this.electricFee = electricFee;
    }

    public int getNoRoom() {
        return roomHisotry.getNoRoom();
    }

    public String getKey() {
        return roomHisotry.getKey();
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getRoomFee() {
        return roomHisotry.getDefaultFeeRoom();
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
        return roomHisotry.getDefaultParkingFee();
    }

    public int getParkingFeePaid() {
        return parkingFeePaid;
    }

    public RoomHistory getRoom() {
        return roomHisotry;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRoomFee(int roomFee) {
        roomHisotry.setDefaultFeeRoom(roomFee);
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
        roomHisotry.setDefaultParkingFee(parkingFee);
    }

    public void setParkingFeePaid(int parkingFeePaid) {
        this.parkingFeePaid = parkingFeePaid;
    }

    public void setRoom(RoomHistory roomHisotry) {
        this.roomHisotry = roomHisotry;
    }
}
