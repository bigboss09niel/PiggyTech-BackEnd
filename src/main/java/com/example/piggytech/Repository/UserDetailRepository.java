package com.example.piggytech.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Role;
import com.example.piggytech.Model.UserAuth;
import com.example.piggytech.Model.UserDetail;

public interface UserDetailRepository  extends  JpaRepository<UserDetail, Long>{

    Optional<Role> findByUserAuth(UserAuth userAuth);

}