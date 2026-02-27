package com.project.order_service.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    @NotBlank(message = "Store ID is required")
    private String storeId;

    @NotEmpty(message = "Items list cannot be empty")
    private List<ItemsDTO> items;
}
