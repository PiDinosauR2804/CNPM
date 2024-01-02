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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int noRoom;
    
    private String idOwner;
    private String nameOwner;
    private String numberPhoneOwner;
    private int defaultFeeRoom;
    private int defaultParkingFee;
    @Column(name = "`key`")
    private String key;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    private List<Resident> residents;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    private List<MandatoryFee> mandatoryFees;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    private List<DonationFee> donationFees;

    public Room() {
        this.residents = new ArrayList<Resident>();
        this.mandatoryFees = new ArrayList<MandatoryFee>();
        this.donationFees = new ArrayList<DonationFee>();
    }

    // Constructor với tham số
    public Room(int noRoom, String idOwner, String nameOwner, String numberPhoneOwner, int defaultFeeRoom, int defaultParkingFee) {
        this.noRoom = noRoom;
        this.idOwner = idOwner;
        this.nameOwner = nameOwner;
        this.numberPhoneOwner = numberPhoneOwner;
        this.defaultFeeRoom = defaultFeeRoom;
        this.defaultParkingFee = defaultParkingFee;
        this.residents = new ArrayList<Resident>();
        this.mandatoryFees = new ArrayList<MandatoryFee>();
        this.donationFees = new ArrayList<DonationFee>();
    }

    public void addResident(Resident resident) {
        residents.add(resident);
    }

    public void addMandatoryFee(MandatoryFee fee) {
        mandatoryFees.add(fee);
    }

    public void eraseMandatoryFee(int index) {
        mandatoryFees.remove(index);
    }

    public void addDonationFee(DonationFee fee) {
        donationFees.add(fee);
    }

    public List<Resident> getResidents(){
        return residents;
    }

    public List<MandatoryFee> getMandatoryFees(){
        return mandatoryFees;
    }

    public List<DonationFee> getDonationFees(){
        return donationFees;
    }

    public void generateKey() {
        String suffix = idOwner.substring(idOwner.length() - 4);
        this.key = suffix + String.valueOf(id);
    }

    public int getId() {
        return id;
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

}
