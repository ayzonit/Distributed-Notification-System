package com.project.order_service.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    private Integer quantity;

    @Column(name = "pricePerItem_id")
    private Double pricePerItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderModel order;
}