package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class DonationFeeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private int month;
    private int year;
    private String typeMoney;
    private int amount;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "`key`", referencedColumnName = "`key`"),
        @JoinColumn(name = "noRoom", referencedColumnName = "noRoom"),
    })
    private RoomHistory roomHistory;

    public DonationFeeHistory() {
    }

    public DonationFeeHistory(int month, int year, int amount, String TypeMoney) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.typeMoney = TypeMoney;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTypeMoney() {
        return typeMoney;
    }

    public void setType_donation(String typeMoney) {
        this.typeMoney = typeMoney;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RoomHistory getRoom() {
        return roomHistory;
    }

    public void setRoom(RoomHistory roomHistory) {
        this.roomHistory = roomHistory;
    }
    public int getNoRoom() {
        return roomHistory.getNoRoom();
    }
}
