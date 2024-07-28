package com.example.piggytech.Controllers;

import com.example.piggytech.DTO.OrderDTO;
import com.example.piggytech.DTO.OrderItemDTO;
import com.example.piggytech.Model.Order;
import com.example.piggytech.Model.OrderItem;
import com.example.piggytech.Model.UserAuth;
import com.example.piggytech.Repository.OrderItemRepository;
import com.example.piggytech.Repository.OrderRepository;
import com.example.piggytech.Repository.UserAuthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date; // Import Date
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/all")
public ResponseEntity<List<Map<String, Object>>> getAllOrders() {
    List<OrderItem> orderItems = orderItemRepository.findAll();

    // Group order items by their order
    Map<Order, List<OrderItem>> groupedOrders = orderItems.stream()
            .collect(Collectors.groupingBy(OrderItem::getOrder));

    // Build the response
    List<Map<String, Object>> response = groupedOrders.entrySet().stream().map(entry -> {
        Order order = entry.getKey();
        List<OrderItem> items = entry.getValue();

        List<Map<String, Object>> products = items.stream().map(orderItem -> {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("productName", orderItem.getProduct().getProductName());
            productMap.put("price", orderItem.getPrice());
            productMap.put("quantity", orderItem.getQuantity());
            return productMap;
        }).collect(Collectors.toList());

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", order.getId());
        userMap.put("totalAmount", order.getTotalAmount());
        userMap.put("orderDate", order.getOrderDate());
        userMap.put("username", order.getUsername());
        userMap.put("products", products); // Add the list of products

        return userMap;
    }).collect(Collectors.toList());

    return new ResponseEntity<>(response, HttpStatus.OK);
}


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> new ResponseEntity<>(o, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        UserAuth userAuth = userAuthRepository.findByUsername(orderDTO.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Create Order object
        Order order = new Order();
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderDate(new Date()); // Use current date
        order.setUsername(orderDTO.getUsername()); // Set the email
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
