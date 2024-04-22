package com.ks.fastfoodapi.dto;

import com.ks.fastfoodapi.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String productName;

    private Long restaurantId;

    @NotBlank(message = "Delivery address is required")
    private String deliveryAddress;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^(05[0-9]{9})$", message = "Invalid Turkish phone number")
    private String contactNumber;

    private OrderStatus status = OrderStatus.ORDER_RECEIVED;

    private Long customerId;
}
