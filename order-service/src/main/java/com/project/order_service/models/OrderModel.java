package com.project.order_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Builder
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderid;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "store_id", nullable = false)
    private String storeId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
