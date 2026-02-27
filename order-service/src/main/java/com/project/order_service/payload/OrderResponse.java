package com.project.order_service.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderResponse {
    private String orderId;
    private String userId;
    private String storeId;
    private List<ItemsDTO> items;
    private LocalDateTime createdAt;
    private String status;
}
