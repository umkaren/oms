package com.oms.ordermanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank (message = "Name required")
    private String customerName;

    @NotNull
    private LocalDate orderDate;

    @NotBlank(message = "Shipping address required")
    private String shippingAddress;

    @Positive(message = "Total cannot be negative")
    private double total;

    public Order(String customerName, LocalDate orderDate, String shippingAddress, double total) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.total = total;
    }

}
