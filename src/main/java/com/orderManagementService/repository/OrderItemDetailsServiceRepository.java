package com.orderManagementService.repository;
import com.orderManagementService.model.OrderItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDetailsServiceRepository extends JpaRepository<OrderItemDetails, Long> {

}

