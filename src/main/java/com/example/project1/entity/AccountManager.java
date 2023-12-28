package com.example.project1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
public class AccountManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String username;
    private String password;

    public AccountManager() {
    }

    // Constructor với tham số
    public AccountManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}