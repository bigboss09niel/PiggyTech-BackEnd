package com.example.piggytech.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserDetail {

    private @Id
    @GeneratedValue Long id;
    private Long userAuthId;
    private String address;
    private String phone;
    private String photo;
    private Date createdAt;


    UserDetail(){}

    public UserDetail( Long userAuthId, String address, String phone, String photo, Date createdAt) {
        this.userAuthId = userAuthId;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.createdAt = createdAt;
    }

    //setters
    public void setUserAuthId(Long userAuthId) {
        this.userAuthId = userAuthId;
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

    //getters
    public Long getId() {
        return id;
    }
    public Long getUserAuthId() {
        return userAuthId;
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
