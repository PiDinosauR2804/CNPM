package com.example.project1.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class RoomHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int noRoom;
    
    private String idOwner;
    private String nameOwner;
    private String numberPhoneOwner;
    private String dayIn;
    private String dayOut;
    private int defaultFeeRoom;
    private int defaultParkingFee;
    @Column(name = "`key`")
    private String key;

    @OneToMany(mappedBy = "roomHistory", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    private List<ResidentHistory> ResidentsHistory;

    @OneToMany(mappedBy = "roomHistory", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    private List<MandatoryFeeHistory> mandatoryFeeHistories;

    @OneToMany(mappedBy = "roomHistory", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    private List<DonationFeeHistory> donationFees;

    public RoomHistory() {
        this.ResidentsHistory = new ArrayList<ResidentHistory>();
        this.mandatoryFeeHistories = new ArrayList<MandatoryFeeHistory>();
        this.donationFees = new ArrayList<DonationFeeHistory>();
    }
    
    // Constructor với tham số
    public RoomHistory(String key, int noRoom, String idOwner, String nameOwner, String numberPhoneOwner, int defaultFeeRoom, int defaultParkingFee, String dayIn) {
        this.key = key;
        this.noRoom = noRoom;
        this.idOwner = idOwner;
        this.nameOwner = nameOwner;
        this.numberPhoneOwner = numberPhoneOwner;
        this.defaultFeeRoom = defaultFeeRoom;
        this.defaultParkingFee = defaultParkingFee;
        this.ResidentsHistory = new ArrayList<ResidentHistory>();
        this.mandatoryFeeHistories = new ArrayList<MandatoryFeeHistory>();
        this.donationFees = new ArrayList<DonationFeeHistory>();
        this.dayIn = dayIn;
    }

    public void addResident(ResidentHistory resident) {
        ResidentsHistory.add(resident);
    }

    public void eraseResident(int index) {
        ResidentsHistory.remove(index);
    }

    public void addMandatoryFee(MandatoryFeeHistory fee) {
        mandatoryFeeHistories.add(fee);
    }

    public void addDonationFee(DonationFeeHistory fee) {
        donationFees.add(fee);
    }

    public List<ResidentHistory> getResidents(){
        return ResidentsHistory;
    }

    public List<MandatoryFeeHistory> getMandatoryFees(){
        return mandatoryFeeHistories;
    }

    public List<DonationFeeHistory> getDonationFees(){
        return donationFees;
    }

    public void generateKey() {
        String suffix = idOwner.substring(idOwner.length() - 4);
        this.key = suffix + String.valueOf(id);
    }

    public int getId() {
        return id;
    }

    public int getDefaultFeeRoom() {
        return defaultFeeRoom;
    }

    public void setDefaultFeeRoom(int defaultFeeRoom) {
        this.defaultFeeRoom = defaultFeeRoom;
    }

    public int getDefaultParkingFee() {
        return defaultParkingFee;
    }

    public void setDefaultParkingFee(int defaultParkingFee) {
        this.defaultParkingFee = defaultParkingFee;
    }

    public int getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(int noRoom) {
        this.noRoom = noRoom;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNumberPhoneOwner() {
        return numberPhoneOwner;
    }

    public void setNumberPhoneOwner(String numberPhoneOwner) {
        this.numberPhoneOwner = numberPhoneOwner;
    }


    public String getKey() {
        return key;
    }

    public String getDayOut() {
        return dayOut;
    }

    public String getDayIn() {
        return dayIn;
    }

    public void setdayOut(String dayOut) {
        this.dayOut = dayOut;
    }

    public void setdayIn(String dayIn) {
        this.dayIn = dayIn;
    }

}
