package com.example.piggytech.Controllers;

import com.example.piggytech.DTO.OrderDTO;
import com.example.piggytech.Model.Order;
import com.example.piggytech.Model.UserAuth;
import com.example.piggytech.Repository.OrderRepository;
import com.example.piggytech.Repository.UserAuthRepository; // Import the UserAuthRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date; // Import Date
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserAuthRepository userAuthRepository; // Add the UserAuthRepository

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> new ResponseEntity<>(o, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        UserAuth userAuth = userAuthRepository.findByEmail(orderDTO.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Create Order object
        Order order = new Order();
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderDate(new Date()); // Use current date
        order.setEmail(orderDTO.getEmail()); // Set the email
        order.setUserAuth(userAuth); // Set the UserAuth object

        Order savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderRepository.findById(id)
            .map(order -> {
                order.setTotalAmount(orderDetails.getTotalAmount());
                order.setOrderDate(orderDetails.getOrderDate());
                order.setUserAuth(orderDetails.getUserAuth());
                Order updatedOrder = orderRepository.save(order);
                return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
