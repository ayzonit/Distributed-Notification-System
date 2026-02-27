package com.project.order_service.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemsDTO {
    @NotBlank
    private String productId;

    @Positive
    private Integer quantity;

    @Positive
    private Double price;
}
