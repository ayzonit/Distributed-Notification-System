package com.project.order_service.payload;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String orderId;
    private String userId;
    private String storeId;
    private List<ItemsDTO> items;
    private LocalDateTime createdAt;
    private String status;
}
