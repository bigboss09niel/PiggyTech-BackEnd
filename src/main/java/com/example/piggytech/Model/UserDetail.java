package com.example.piggytech.Model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_detail")
public class UserDetail {

    private @Id
    @GeneratedValue Long userDetailId;
    private String address;
    private String phone;
    private String photo;
    @CreationTimestamp
    private Date createdAt;

    // Defining the many-to-many relationship with User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "userauth_userdetail",
        joinColumns = { @JoinColumn(name = "user_detail_id", referencedColumnName = "userDetailId") },
        inverseJoinColumns = { @JoinColumn(name = "user_auth_id", referencedColumnName = "id") }
    )
    private UserAuth userAuth;

    UserDetail(){}

    // Constructors
    public UserDetail(String address, String phone, String photo, Date createdAt) {
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.createdAt = createdAt;
    }

    // Setters
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
    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    // Getters
    public Long getIdDetailId() {
        return userDetailId;
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
    public UserAuth getUserAuth() {
        return userAuth;
    }

}