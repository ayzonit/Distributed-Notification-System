package com.project.order_service.repository;

import com.project.order_service.models.OrderModel;
import com.project.order_service.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, String> {
    List<OrderModel> findByUserId(String userId);
    List<OrderModel> findByStoreId(String storeId);
    List<OrderModel> findByStatus(Status statusId);
}
