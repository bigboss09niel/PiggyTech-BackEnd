package com.example.piggytech.DTO;

import java.util.Date;

public class UserDetailDTO {

    private String email;
    private String address;
    private String phone;
    private String photo;
    private Date createdAt;

    UserDetailDTO(){}

    public UserDetailDTO(String email, String address, String phone, String photo, Date createdAt) {
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
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
    public void setPhoto(String photo) {
        this.photo = photo;
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
    public String getPhoto() {
        return photo;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

}
