package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findTop5ByOrderByCreatedDateDesc(); // Lấy 5 đơn hàng gần đây nhất
}
