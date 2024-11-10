package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    Page<Order> findByAssignedStaffId(Long staffId, Pageable pageable);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findAllByCreatedAtAfter(LocalDateTime date);

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    Double calculateTotalRevenueBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    Integer countOrdersBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
