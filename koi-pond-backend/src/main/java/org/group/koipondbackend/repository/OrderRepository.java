package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByProjectId(Long projectId); // Tìm kiếm đơn hàng theo projectId
}