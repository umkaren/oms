package com.oms.ordermanagementsystem.controller;

import com.oms.ordermanagementsystem.model.Order;
import com.oms.ordermanagementsystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order orders) {
        try {
            return new ResponseEntity<>(orderService.createNewOrder(orders), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<Order>> listAllOrders(){
        try {
            List<Order> listOfOrders = orderService.listAllOrders();
            if (listOfOrders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listOfOrders, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Order> readOrder(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(orderService.findExistingOrder(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @Valid @RequestBody Order newOrder){
        try {
            orderService.updateOrder(id, newOrder);
            return new ResponseEntity<>(orderService.findExistingOrder(id), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Long id){
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
