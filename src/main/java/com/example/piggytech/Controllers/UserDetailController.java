package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.UserDetail;
import com.example.piggytech.NotFoundException.UserDetailNotFoundException;
import com.example.piggytech.Repository.UserDetailRepository;

@RestController
@RequestMapping("/api/v1/userdetail")
public class UserDetailController {


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
    public UserDetail getUserDetailById(@PathVariable Long id) {
        return repo.findById(id)
        .orElseThrow(()-> new UserDetailNotFoundException(id));
    }

    //CREATE UserDetail
    @PostMapping("/new")
    public String addUserDetail(@RequestBody UserDetail newUserDetail) {
        repo.save(newUserDetail);
        return "A new User Detail is added. Yey!";
    }

     //UPDATE ENDPOINTS
    @PutMapping("/edit/{id}")
    public UserDetail updateUserDetail(@PathVariable Long id, 
    @RequestBody UserDetail newUserDetail) {
        return repo.findById(id)
        .map(userDetail -> {
            userDetail.setUserAuthId(newUserDetail.getUserAuthId());
            userDetail.setAddress(newUserDetail.getAddress());
            userDetail.setPhone(newUserDetail.getPhone());
            userDetail.setPhoto(newUserDetail.getPhoto());
            userDetail.setCreatedAt(newUserDetail.getCreatedAt());
            return repo.save(userDetail);
        }).orElseGet(()->{
            return repo.save(newUserDetail);
        });
    }




}
