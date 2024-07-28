package com.example.piggytech.Controllers;

import com.example.piggytech.DTO.OrderItemDTO;
import com.example.piggytech.Model.Order;
import com.example.piggytech.Model.OrderItem;
import com.example.piggytech.Model.Product;
import com.example.piggytech.Repository.OrderItemRepository;
import com.example.piggytech.Repository.OrderRepository;
import com.example.piggytech.Repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderitem")
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemController(OrderItemRepository orderItemRepository,
                               OrderRepository orderRepository,
                               ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderItem>> getOrderItems() {
        List<OrderItem> items = orderItemRepository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        return orderItemRepository.findById(id)
            .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    public ResponseEntity<String> addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
            .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(orderItemDTO.getProductId())
            .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < orderItemDTO.getQuantity()) {
            return new ResponseEntity<>("Not enough product quantity available.", HttpStatus.BAD_REQUEST);
        }

        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setQuantity(orderItemDTO.getQuantity());
        newOrderItem.setPrice(orderItemDTO.getPrice());
        newOrderItem.setOrder(order);
        newOrderItem.setProduct(product);

        // Reduce the product stock and increase the product sold
        product.setStock(product.getStock() - orderItemDTO.getQuantity());
        product.setSold(product.getSold() + orderItemDTO.getQuantity());
        productRepository.save(product);

        orderItemRepository.save(newOrderItem);
        return new ResponseEntity<>("A new order item is added.", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return new ResponseEntity<>("Order item deleted.", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Order item not found.", HttpStatus.NOT_FOUND);
        }
    }
}
