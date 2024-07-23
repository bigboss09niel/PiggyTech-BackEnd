package com.example.piggytech.DTO;

import java.util.Date;

public class UserDetailDTO {

    private String email;
    private String address;
    private String phone;
    private String gender;
    private Date createdAt;

    UserDetailDTO(){}

    // Constructors
    public UserDetailDTO(String email, String address, String phone, String gender, Date createdAt) {
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getters
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getGender() {
        return gender;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

}