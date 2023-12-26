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

    private int id_money;
    private int amount;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "`key`", referencedColumnName = "`key`"),
        @JoinColumn(name = "noRoom", referencedColumnName = "noRoom"),
    })
    private RoomHistory roomHisotry;

    public DonationFeeHistory() {
    }

    public DonationFeeHistory(int month, int year, int id_money, int amount) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.id_money = id_money;
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

    public int getId_money() {
        return id_money;
    }

    public void setId_money(int id_money) {
        this.id_money = id_money;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RoomHistory getRoom() {
        return roomHisotry;
    }

    public void setRoom(RoomHistory roomHisotry) {
        this.roomHisotry = roomHisotry;
    }
    public int getNoRoom() {
        return roomHisotry.getNoRoom();
    }
}
