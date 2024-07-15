package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.UserDetail;

public interface UserDetailRepository  extends  JpaRepository<UserDetail, Long>{

}
