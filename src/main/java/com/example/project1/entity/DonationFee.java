package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class DonationFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private int month;
    private int year;

    private int amount;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "`key`", referencedColumnName = "`key`"),
        @JoinColumn(name = "noRoom", referencedColumnName = "noRoom"),
    })
    private Room room;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "type")
    private TypeDonation typeDonation;

    public DonationFee() {
    }

    public DonationFee(int month, int year, int amount) {
        this.month = month;
        this.year = year;
        this.amount = amount;
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

    public String getType() {
        return typeDonation.getType();
    }

    public void setTypeDonation(TypeDonation type) {
        this.typeDonation = type;
    }

    public TypeDonation getTypeDonation() {
        return typeDonation;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    public int getNoRoom() {
        return room.getNoRoom();
    }
}
