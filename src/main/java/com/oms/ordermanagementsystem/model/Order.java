package com.oms.ordermanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

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

}
