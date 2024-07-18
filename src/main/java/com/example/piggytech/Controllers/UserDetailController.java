package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.DTO.UserDetailDTO;
import com.example.piggytech.Model.Inventory;
import com.example.piggytech.Model.UserAuth;
import com.example.piggytech.Model.UserDetail;
import com.example.piggytech.NotFoundException.UserDetailNotFoundException;
import com.example.piggytech.Repository.UserAuthRepository;
import com.example.piggytech.Repository.UserDetailRepository;

@RestController
@RequestMapping("/api/v1/userdetail")
public class UserDetailController {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    UserDetailRepository repo;

    public UserDetailController(UserDetailRepository repo) {
        this.repo = repo;
    }

    //GET ALL USERDETAIL
    @GetMapping("/all")
    public List<UserDetail> getUserDetail() {
        return repo.findAll();
    }
    
    // GET One userdetail
    @GetMapping("/{id}")
    public UserDetail getUserAuth(@PathVariable Long id) {
        return userDetailRepository.findById(id)
        .orElseThrow(()-> new UserDetailNotFoundException(id));
    }

    //CREATE UserDetail
    @PostMapping("/new")
    public ResponseEntity<?> addUserDetailEntity(@RequestBody UserDetailDTO entity) {
        UserDetail userDetail = new UserDetail(
            entity.getAddress(),
            entity.getPhone(),
            entity.getPhoto(),
            entity.getCreatedAt()
        );
        UserAuth userAuth = userAuthRepository.findByEmail(entity.getEmail());
        userDetail.setUserAuth(userAuth);
        userDetailRepository.save(userDetail);
        return new ResponseEntity<>("User details add successfully", HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public UserDetail updateUserDetail(@PathVariable Long id,
    @RequestBody UserDetailDTO entity){
        UserDetail newUserDetail = new UserDetail(
            entity.getAddress(),
            entity.getPhone(),
            entity.getPhoto(),
            entity.getCreatedAt()
        );
        UserAuth userAuth = userAuthRepository.findByEmail(entity.getEmail());
        return userDetailRepository.findById(id)
        .map(userDetail ->{
            userDetail.setAddress(newUserDetail.getAddress());
            userDetail.setPhone(newUserDetail.getPhone());
            userDetail.setPhoto(newUserDetail.getPhoto());
            userDetail.setCreatedAt(newUserDetail.getCreatedAt());
            userDetail.setUserAuth(userAuth);
            return userDetailRepository.save(userDetail);
        }).orElseGet(()->{
            return userDetailRepository.save(newUserDetail);
        });
    }

}
