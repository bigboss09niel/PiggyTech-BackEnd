package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.Order;
import com.example.piggytech.NotFoundException.OrderNotFoundException;
import com.example.piggytech.Repository.OrderRepository;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    
    final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    // GET ALL SALES
    @GetMapping("/all")
    public List<Order> getOrder(){
        return repo.findAll();
    }

    // GET ONE INVENTORY
    @GetMapping("/{id}")
    public Order getSalesyById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow(()-> new OrderNotFoundException(id));
    }

    // CREATE ENDPOINTS
    @PostMapping("/new")
    public String addOrder(@RequestBody Order newOrder){
        repo.save(newOrder);
        return "A new Order is added!";
    }

    // DELETE ENDPOINTS
    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id){
        repo.deleteById(id);
        return "A order is deleted!";
    }

}