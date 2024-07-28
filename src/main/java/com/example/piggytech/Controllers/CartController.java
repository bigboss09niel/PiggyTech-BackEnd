package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.Cart;
import com.example.piggytech.NotFoundException.CartNotFoundException;
import com.example.piggytech.Repository.CartRepository;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    final CartRepository repo;

    public CartController (CartRepository repo){
        this.repo = repo;
    }

    @GetMapping("/all")
    public List<Cart>getCart(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Cart  getCartById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow (()-> new CartNotFoundException(id));
    }  

    @PostMapping("/new")
    public String addCart(@RequestBody Cart newCart){
        repo.save(newCart);
        return "A new Cart is added!";

    }

   @DeleteMapping ("/delete/{id}")
   public String deleteCart(@PathVariable Long id){
     repo.deleteById(id);
     return "An Cart is deleted!";
   }
}
