package com.ks.fastfoodapi.model;

import com.ks.fastfoodapi.enums.OrderStatus;
import com.ks.fastfoodapi.enums.ProductName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductName productName;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @NotBlank(message = "Delivery address is required")
    @Column(columnDefinition = "TEXT")
    private String deliveryAddress;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^(05[0-9]{9})$", message = "Invalid Turkish phone number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ORDER_RECEIVED;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
}
