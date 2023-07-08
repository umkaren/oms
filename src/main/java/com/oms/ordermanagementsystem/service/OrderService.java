package com.oms.ordermanagementsystem.service;

import com.oms.ordermanagementsystem.model.Order;
import com.oms.ordermanagementsystem.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository){
        this.repository = repository;
    }
    public Order createNewOrder(Order orders){
        return repository.save(orders);
    }
    public Order findExistingOrder(Long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + id));
    }
    public List<Order> listAllOrders(){
        return repository.findAll();
    }
    public Order updateOrder(Long id, Order orders){
        Order existingOrder = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + id));
        existingOrder.setCustomerName(orders.getCustomerName());
        existingOrder.setOrderDate(orders.getOrderDate());
        existingOrder.setShippingAddress(orders.getShippingAddress());
        existingOrder.setTotal(orders.getTotal());
        return repository.save(existingOrder);
    }
    public void deleteOrder(Long id){
        repository.deleteById(id);
    }
}
