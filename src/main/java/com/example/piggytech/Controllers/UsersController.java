package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.User;
import com.example.piggytech.NotFoundException.UserNotFoundException;
import com.example.piggytech.Repository.UserRepository;

@RestController
public class UsersController {
    
    UserRepository repo;

    public UsersController(UserRepository repo) {
        this.repo = repo;
    }

    // GET ALL USERS
    // http://127.0.0.1:8080/users
    @GetMapping("/users")
    public List<User> getUsers(){
        return repo.findAll();
    }

    // GET ONE USER
    // http://127.0.0.1:8080/user/1
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow(()-> new UserNotFoundException(id));
    }

    // CREATE ENDPOINTS
    // http://127.0.0.1:8080/user/new
    @PostMapping("/user/new")
    public String addUser(@RequestBody User newUser){
        repo.save(newUser);
        return "A new user is added. Yey!";
    }

    // UPDATE ENDPOINTS
    // http://127.0.0.1:8080/user/edit/1
    @PutMapping("/user/edit/{id}")
    public User updateUser(@PathVariable Long id,
    @RequestBody User newUser){
        return repo.findById(id)
        .map(user ->{
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            return repo.save(user);
        }).orElseGet(()->{
            return repo.save(newUser);
        });
    }

    // DELETE ENDPOINTS
    // http://127.0.0.1:8080/user/delete/1
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        repo.deleteById(id);
        return "A user is deleted!";
    }
}
