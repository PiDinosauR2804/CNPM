package com.example.project1.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TypeDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMoney;
    private String type;

    @OneToMany(mappedBy = "typeDonation", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    private List<DonationFee> donationFees;

    public TypeDonation() {
        this.donationFees = new ArrayList<DonationFee>();
    }

    public TypeDonation(String type) {
        this.donationFees = new ArrayList<DonationFee>();
        this.type = type;
    }

    public void addDonationFee(DonationFee fee) {
        donationFees.add(fee);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getIdMoney() {
        return idMoney;
    }
}
